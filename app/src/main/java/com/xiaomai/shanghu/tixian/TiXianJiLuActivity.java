package com.xiaomai.shanghu.tixian;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.google.gson.Gson;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.adapter.Adapter_Withdrawals_Record;
import com.xiaomai.shanghu.base.BaseActivity;
import com.xiaomai.shanghu.bean.Condition;
import com.xiaomai.shanghu.bean.DepositsBean;
import com.xiaomai.shanghu.bean.Info;
import com.xiaomai.shanghu.filter.Filter_TixianActivity;
import com.xiaomai.shanghu.net.RetrofitClient;
import com.xiaomai.shanghu.utils.AddDefaultScreenAreaUtils;
import com.xiaomai.shanghu.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class TiXianJiLuActivity extends BaseActivity {


    @BindView(R.id.xtab)
    XTabLayout xtab;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.bt_filter)
    TextView btFilter;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<String> titleList;
    private List<Fragment> fragmentList;

    private List<DepositsBean.DataBean.ListBean> list;
    private Adapter_Withdrawals_Record adapter;
    private SharedPreferences usertoken,filter_record;
    private String token,state;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ti_xian_ji_lu;
    }

    @Override
    public void initView() {
        AddDefaultScreenAreaUtils.addDefaultScreenArea(btFilter,10,10,10,10);//增加点击范围
        usertoken= getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");

        state = "";

//        titleList = new ArrayList<>();
//        titleList.add("提现中");
//        titleList.add("已提现");
//        titleList.add("未通过");
//        fragmentList = new ArrayList<>();
//        fragmentList.add(new TiXianZhong());
//        fragmentList.add(new YiTiXian());
//        fragmentList.add(new WeiTongGuo());
//
//        viewpage.setAdapter(new TabAdapter(getSupportFragmentManager(), titleList, fragmentList));
//        viewpage.setOffscreenPageLimit(0);
//        xtab.setupWithViewPager(viewpage);
//        xtab.getTabAt(0).select();
//        xtab.getTabAt(1).select();
//        viewpage.setCurrentItem(0);


        //配置recycleView
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layout);

        list = new ArrayList<>();

        adapter=new Adapter_Withdrawals_Record(R.layout.item_withdrawals_record,list);
        View inflate = LayoutInflater.from(this).inflate(R.layout.empty_view, null, false);
        recycler.addItemDecoration(new SpacesItemDecoration(12));
        adapter.setEmptyView(inflate);
        recycler.setAdapter(adapter);
        adapter.openLoadAnimation();

        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(state);
            }
        });

        getData(state);
    }

    @OnClick({R.id.back, R.id.bt_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bt_filter:
                toClass(this,Filter_TixianActivity.class);
                break;
        }
    }

    private void getData(String state) {
        //body传参
        Condition condition = new Condition("","","",""+state,null);
        Info info = new Info(1+"",30+"",condition);
        Gson gson = new Gson();
        String strJson = gson.toJson(info);
        Log.d("tag", "json----" + strJson);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), strJson);
        RetrofitClient.getInstance().getApi(token).getDepositBean(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<DepositsBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<DepositsBean>() {
                    @Override
                    public void accept(DepositsBean bean) throws Exception {
                        list.clear();
                        if (bean.getCode() == 1) {
                            for (int i = 0;i<bean.getData().getList().size();i++){
                                list.add(bean.getData().getList().get(i));
                            }
                        }else if(bean.getCode()==-10 || bean.getMsg().equals("您的账户已在其他设备上登录")){
                            signOutDialog(TiXianJiLuActivity.this);
                            usertoken.edit().clear().commit();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        swipeRefreshLayout.setRefreshing(false);
                        if (throwable.getMessage().equals("HTTP 401 ")){
                            signOutDialog(TiXianJiLuActivity.this);
                            usertoken.edit().clear().commit();
                        }
                        Log.d("tag", "请求错误");
                    }
                });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        filter_record = getSharedPreferences("filter_record", 0);
        state = filter_record.getString("state","");

        getData(state);
    }
}
