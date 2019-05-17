package com.xiaomai.shanghu.freeze;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.adapter.TabAdapter;
import com.xiaomai.shanghu.base.BaseActivity;
import com.xiaomai.shanghu.bean.GetFreezeCountBean;
import com.xiaomai.shanghu.filter.Filter_Freeze_SheBeiActivity;
import com.xiaomai.shanghu.net.RetrofitClient;
import com.xiaomai.shanghu.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FreezeActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.xtab)
    XTabLayout xtab;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.bt_filter)
    TextView btFilter;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.tv_money)
    TextView tv_money;

    private List<String> titleList;
    private List<Fragment> fragmentList;

    SharedPreferences usertoken;
    String token;
    Dialog dialog;
    @Override
    public int getLayoutId() {
        return R.layout.activity_freeze;
    }

    @Override
    public void initView() {
        usertoken= getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");

        dialog = DialogUtils.showDialog_progressbar(FreezeActivity.this);

        titleList = new ArrayList<>();
        titleList.add("未解冻");
        titleList.add("已解冻");
        titleList.add("已回款");
        fragmentList = new ArrayList<>();
        fragmentList.add(new NoFrozenFragment());
        fragmentList.add(new FrozenFragment());
        fragmentList.add(new ReturnMoneyFragment());

        viewpage.setAdapter(new TabAdapter(getSupportFragmentManager(), titleList, fragmentList));
        viewpage.setOffscreenPageLimit(0);
        xtab.setupWithViewPager(viewpage);
        xtab.getTabAt(0).select();
        xtab.getTabAt(1).select();
        viewpage.setCurrentItem(0);

        getFreezeCount();
    }


    @OnClick({R.id.back, R.id.bt_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bt_filter:
                toClass(this, Filter_Freeze_SheBeiActivity.class);
                break;
        }
    }

    public void getFreezeCount(){
       RetrofitClient.getInstance().getApi(token).getFreezeCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               .as(AutoDispose.<GetFreezeCountBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
               .subscribe(new Consumer<GetFreezeCountBean>() {
                               @Override
                               public void accept(GetFreezeCountBean getFreezeCountBean) throws Exception {
                                   if (getFreezeCountBean.getCode()==1){
                                       tv_number.setText(getFreezeCountBean.getData().getFreezeCount()+"");
                                       tv_money.setText(getFreezeCountBean.getData().getFreezeMoney()+"");
                                   }else if(getFreezeCountBean.getCode()==-10){
                                       signOutDialog(FreezeActivity.this);
                                       usertoken.edit().clear().commit();
                                   }
                                   DialogUtils.closeDialog(dialog);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.d("tag", "请求错误");
                                   DialogUtils.closeDialog(dialog);
                                   if (throwable.getMessage().equals("HTTP 401")){
                                       signOutDialog(FreezeActivity.this);
                                       usertoken.edit().clear().commit();
                                   }
                               }
                           }
                  );
    }

    @Override
    protected void onResume() {
        super.onResume();
        getFreezeCount();
    }
}
