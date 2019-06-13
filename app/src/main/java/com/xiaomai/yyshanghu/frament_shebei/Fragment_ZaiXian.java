package com.xiaomai.yyshanghu.frament_shebei;

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
import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.adapter.Adapter_Zaixian;
import com.xiaomai.yyshanghu.base.BaseFragment;
import com.xiaomai.yyshanghu.bean.Condition;
import com.xiaomai.yyshanghu.bean.Info;
import com.xiaomai.yyshanghu.bean.PageDeviceListBean;
import com.xiaomai.yyshanghu.details.SheBeiDetailsActivity;
import com.xiaomai.yyshanghu.net.RetrofitClient;
import com.xiaomai.yyshanghu.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Fragment_ZaiXian extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private Adapter_Zaixian adapter;
    private List<PageDeviceListBean.DataBean.ListBean> bean;
    private SharedPreferences usertoken;
    private String token;
    private Dialog dialog;
    private String deviceld,state,slot;
    private SharedPreferencesUtil sharedPreferencesUtil;
    @Override
    protected void initView(View view) {
        usertoken= getActivity().getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");
        state = 1+"";
        deviceld ="";
        slot = "";
        //配置recycleView
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layout);

        bean = new ArrayList<>();
        adapter=new Adapter_Zaixian(R.layout.item_shebei_zaixian,bean);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.empty_view, null, false);
        adapter.setEmptyView(inflate);
        recycler.setAdapter(adapter);
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
        return R.layout.shebei_zaixian;
    }

    public void getPageDeviceList(String str,String slot){
        if (deviceld.equals("null")){
            deviceld = "";
        }
        if (slot.equals("null")){
            slot = "";
        }
        Condition condition = new Condition("",""+1,""+str,""+slot);
        Info info = new Info(1+"",500+"",condition);
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
                            for (int i = 0; i <pageDeviceListBean.getData().getList().size(); i++) {
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        sharedPreferencesUtil = new SharedPreferencesUtil(getContext());
        deviceld = sharedPreferencesUtil.getSP("deviceld");
        slot =  sharedPreferencesUtil.getSP("slot");

        getPageDeviceList( deviceld, slot);
    }

}
