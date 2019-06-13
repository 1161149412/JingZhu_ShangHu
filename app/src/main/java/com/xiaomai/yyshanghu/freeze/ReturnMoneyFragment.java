package com.xiaomai.yyshanghu.freeze;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.adapter.Adapter_Freeze;
import com.xiaomai.yyshanghu.base.BaseFragment;
import com.xiaomai.yyshanghu.bean.Condition;
import com.xiaomai.yyshanghu.bean.DeviceFreezeListBean;
import com.xiaomai.yyshanghu.bean.Info;
import com.xiaomai.yyshanghu.details.FreezeDetailsActivity;
import com.xiaomai.yyshanghu.net.RetrofitClient;
import com.xiaomai.yyshanghu.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ReturnMoneyFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private Adapter_Freeze adapter;
    private List<DeviceFreezeListBean.DataBean.ListBean> bean;
    private SharedPreferences usertoken,filter_freeze;
    private String token,deviceId,slot;

    @Override
    protected void initView(View view) {
        usertoken= getActivity().getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");
        deviceId = "";
        slot = "";

        //配置recycleView
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layout);

        bean = new ArrayList<>();
        adapter = new Adapter_Freeze(R.layout.item_freeze, bean);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.empty_view, null, false);
        recycler.addItemDecoration(new SpacesItemDecoration(10));
        adapter.setEmptyView(inflate);
        adapter.openLoadAnimation();
        recycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(),FreezeDetailsActivity.class);
                intent.putExtra("id",bean.get(position).getId());
                startActivity(intent);
            }
        });
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDeviceFreezeList(deviceId,slot);
            }
        });
        getDeviceFreezeList(deviceId,slot);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.freeze_fragment;
    }


    public void getDeviceFreezeList(String deviceId,String slot){
        Condition condition = new Condition(3+"",""+deviceId,""+slot);
        Info info = new Info(1+"",500+"",condition);
        Gson gson = new Gson();
        String strJson = gson.toJson(info);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), strJson);
        RetrofitClient.getInstance().getApi(token).getDeviceFreezeList(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<DeviceFreezeListBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<DeviceFreezeListBean>() {
                    @Override
                    public void accept(DeviceFreezeListBean deviceFreezeListBean) throws Exception {
                        bean.clear();
                        for (int i = 0; i < deviceFreezeListBean.getData().getList().size(); i++) {
                            bean.add(deviceFreezeListBean.getData().getList().get(i));
                        }
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("tag", "请求错误");
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

    }

    @Override
    public void onStart() {
        super.onStart();
        filter_freeze= getActivity().getSharedPreferences("filter_freeze", 0);

        deviceId = filter_freeze.getString("deviceId","");
        slot = filter_freeze.getString("slot","");

        getDeviceFreezeList(deviceId,slot);
    }

}
