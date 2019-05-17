package com.xiaomai.shanghu.frament_shebei;

import android.app.Dialog;
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
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.adapter.Adapter_Laixian;
import com.xiaomai.shanghu.base.BaseFragment;
import com.xiaomai.shanghu.bean.Condition;
import com.xiaomai.shanghu.bean.Info;
import com.xiaomai.shanghu.bean.PageDeviceListBean;
import com.xiaomai.shanghu.details.SheBeiDetailsActivity;
import com.xiaomai.shanghu.net.RetrofitClient;
import com.xiaomai.shanghu.utils.SharedPreferencesUtil;
import com.xiaomai.shanghu.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Fragment_LiXian extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<String> list;
    private Adapter_Laixian adapter;
    private List<PageDeviceListBean.DataBean.ListBean> bean;

    SharedPreferences usertoken;
    String token;
    Dialog dialog;
    String deviceld,state,slot;
    SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void initView(View view) {
        usertoken= getActivity().getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");
        sharedPreferencesUtil = new SharedPreferencesUtil(getContext());
        state = 0+"";
        deviceld ="";
        slot = "";

        //配置recycleView
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layout);

        bean = new ArrayList<>();

        adapter=new Adapter_Laixian(R.layout.item_shebei_lixian,bean);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.empty_view, null, false);
        recycler.addItemDecoration(new SpacesItemDecoration(12));
        adapter.setEmptyView(inflate);
        recycler.setAdapter(adapter);
        adapter.openLoadAnimation();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(),SheBeiDetailsActivity.class);
                intent.putExtra("id",bean.get(position).getId());
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPageDeviceList(deviceld,slot);
            }
        });
        getPageDeviceList(deviceld,slot);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.shebei_lixian;
    }

    public void getPageDeviceList(String deviceld,String slot){
        if (deviceld.equals("null")){
            deviceld = "";
        }
        if (slot.equals("null")){
            slot = "";
        }
        Condition condition = new Condition("",""+0,""+deviceld,""+slot);
        Info info = new Info(1+"",30+"",condition);
        Gson gson = new Gson();
        String strJson = gson.toJson(info);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), strJson);
        RetrofitClient.getInstance().getApi(token).getpageDeviceList(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<PageDeviceListBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<PageDeviceListBean>() {
                    @Override
                    public void accept(PageDeviceListBean pageDeviceListBean) throws Exception {
                        bean.clear();
                        if(pageDeviceListBean.getCode() ==1){
                            for (int i = 0; i < pageDeviceListBean.getData().getList().size(); i++) {
                                bean.add(pageDeviceListBean.getData().getList().get(i));
                            }
                        }else if(pageDeviceListBean.getCode() == -10 || pageDeviceListBean.getMsg().equals("您的账户已在其他设备上登录")){
                            signOutDialog();
                            usertoken.edit().clear().commit();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("tag", "请求错误");
                        swipeRefreshLayout.setRefreshing(false);
                        if (throwable.getMessage().equals("HTTP 401 ")){
                            signOutDialog();
                            usertoken.edit().clear().commit();
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();

        deviceld = sharedPreferencesUtil.getSP("deviceld");
        slot =  sharedPreferencesUtil.getSP("slot");
        Log.d("msg", "deviceld:"+deviceld);
        Log.d("msg", "slot:"+slot);
        getPageDeviceList(deviceld, slot);
    }
}
