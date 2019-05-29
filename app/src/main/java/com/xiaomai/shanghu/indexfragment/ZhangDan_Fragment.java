package com.xiaomai.shanghu.indexfragment;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.adapter.Adapter_ZhangDan;
import com.xiaomai.shanghu.base.BaseFragment;
import com.xiaomai.shanghu.bean.Condition;
import com.xiaomai.shanghu.bean.GetIndexBean;
import com.xiaomai.shanghu.bean.GetPageSellerBillBean;
import com.xiaomai.shanghu.bean.Info;
import com.xiaomai.shanghu.net.RetrofitClient;
import com.xiaomai.shanghu.utils.DialogUtils;
import com.xiaomai.shanghu.utils.StringToIntUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ZhangDan_Fragment extends BaseFragment {
    @BindView(R.id.tv_tixian_money)
    TextView tvTixianMoney;
    @BindView(R.id.tv_tixian_dongjie)
    TextView tvTixianDongjie;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<String> list;
    private Adapter_ZhangDan adapter;
    private List<GetPageSellerBillBean.DataBean.ListBean> bean;

    SharedPreferences usertoken;
    String token;
    Dialog dialog;
    @Override
    protected void initView(View view) {
        usertoken= getActivity().getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");
        dialog = DialogUtils.showDialog_progressbar(getContext());

        //配置recycleView
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layout);

        bean = new ArrayList<>();
        adapter = new Adapter_ZhangDan(R.layout.zhangdan_item, bean);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.empty_view, null, false);
        adapter.setEmptyView(inflate);
        recycler.setAdapter(adapter);

        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getpageSellerBill();
                getIndex();
            }
        });
        getIndex();
        getpageSellerBill();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhangdan;
    }

    public void getIndex(){
        RetrofitClient.getInstance().getApi(token).getIndex()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetIndexBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetIndexBean>() {
                               @Override
                               public void accept(GetIndexBean getIndexBean) throws Exception {
                                   DialogUtils.closeDialog(dialog);
                                   if(getIndexBean.getCode()==1){
                                       tvTixianMoney.setText(StringToIntUtils.StringToInt(getIndexBean.getData().getSellerRewardSum()+""));
                                       tvTixianDongjie.setText(StringToIntUtils.StringToInt(getIndexBean.getData().getOrderRewardSum()+""));
                                   }else if(getIndexBean.getCode() == -10 || getIndexBean.getMsg().equals("您的账户已在其他设备上登录")){
                                       signOutDialog();
                                       usertoken.edit().clear().commit();
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.d("tag", "请求错误"+throwable.getMessage().toString());
                                   DialogUtils.closeDialog(dialog);
                                   if (throwable.getMessage().equals("HTTP 401 ")){
                                       signOutDialog();
                                       usertoken.edit().clear().commit();
                                   }
                               }
                           }
                );
    }

    private void getpageSellerBill(){
        Condition condition = new Condition(null,null,null,null);
        Info info = new Info(1+"",30+"",condition);

        Gson gson = new Gson();
        String strJson = gson.toJson(info);

        Log.d("tag", ""+strJson.toString());

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), strJson);
        RetrofitClient.getInstance().getApi(token).getpageSellerBill(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetPageSellerBillBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetPageSellerBillBean>() {
                    @Override
                    public void accept(GetPageSellerBillBean getPageSellerBillBean) throws Exception {
                        bean.clear();
                        for (int i = 0; i < getPageSellerBillBean.getData().getList().size(); i++) {
                            bean.add(getPageSellerBillBean.getData().getList().get(i));
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
    public void onResume() {
        super.onResume();
        getIndex();
        getpageSellerBill();
    }
}
