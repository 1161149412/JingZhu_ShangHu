package com.xiaomai.shanghu.indexfragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.adapter.TabAdapter;
import com.xiaomai.shanghu.base.BaseFragment;
import com.xiaomai.shanghu.bean.GetDviceCountBean;
import com.xiaomai.shanghu.filter.Filter_SheBeiActivity;
import com.xiaomai.shanghu.frament_shebei.Fragment_LiXian;
import com.xiaomai.shanghu.frament_shebei.Fragment_ZaiXian;
import com.xiaomai.shanghu.freeze.FreezeActivity;
import com.xiaomai.shanghu.net.RetrofitClient;
import com.xiaomai.shanghu.utils.AddDefaultScreenAreaUtils;
import com.xiaomai.shanghu.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SheBei_Fragment extends BaseFragment {

    @BindView(R.id.tv_zaixian)
    TextView tv_shebei_zaixian;
    @BindView(R.id.tv_lixian)
    TextView tv_shebei_lixian;
    @BindView(R.id.bt_shebei_dongjie)
    TextView btShebeiDongjie;
    @BindView(R.id.xtab)
    XTabLayout xtab;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.bt_filter)
    TextView btFilter;

    public final int REQUESTCODE_FROM_MAIN_TO_OTHER = 1;
    public final int REQUESTCODE_FROM_MAIN_TO_PEPELU = 2;

    private List<String> titleList;
    private List<Fragment> fragmentList;
    private SharedPreferences usertoken;
    private String token;
    private Dialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shebei;
    }

    @Override
    protected void initView(View view) {
        AddDefaultScreenAreaUtils.addDefaultScreenArea(btFilter,10,10,30,10);//增加点击范围
        usertoken= getActivity().getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");
        dialog = DialogUtils.showDialog_progressbar(getContext());

        titleList = new ArrayList<>();
        titleList.add("在线设备");
        titleList.add("离线设备");
        fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment_ZaiXian());
        fragmentList.add(new Fragment_LiXian());

        viewpage.setAdapter(new TabAdapter(getActivity().getSupportFragmentManager(), titleList, fragmentList));
        viewpage.setOffscreenPageLimit(0);
        xtab.setupWithViewPager(viewpage);
        xtab.getTabAt(0).select();
        xtab.getTabAt(1).select();
        viewpage.setCurrentItem(0);

        getData();
    }

    @OnClick({R.id.bt_filter, R.id.bt_shebei_dongjie})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_filter:
                Intent intent = new Intent(getActivity(),Filter_SheBeiActivity.class);
                startActivityForResult(intent,REQUESTCODE_FROM_MAIN_TO_OTHER);
//                toClass(getActivity(),Filter_SheBeiActivity.class);
                break;
            case R.id.bt_shebei_dongjie:
                toClass(getActivity(),FreezeActivity.class);
                break;
        }
    }

    public void getData(){

        RetrofitClient.getInstance().getApi(token).getDeviceCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetDviceCountBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetDviceCountBean>() {
                    @Override
                    public void accept(GetDviceCountBean getDviceCountBean) throws Exception {
                        if (getDviceCountBean.getCode()==1){
                            tv_shebei_zaixian.setText(getDviceCountBean.getData().getOnline()+"");
                            tv_shebei_lixian.setText(getDviceCountBean.getData().getOffline()+"");
                        }else if(getDviceCountBean.getCode()==-10 || getDviceCountBean.getMsg().equals("您的账户已在其他设备上登录")){
                            signOutDialog();
                            usertoken.edit().clear().commit();
                        }
                        DialogUtils.closeDialog(dialog);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("tag", "请求错误");
                        DialogUtils.closeDialog(dialog);
                        if (throwable.getMessage().equals("HTTP 401 ")){
                            signOutDialog();
                            usertoken.edit().clear().commit();
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUESTCODE_FROM_MAIN_TO_OTHER:
                if (resultCode == Activity.RESULT_OK) {
                    viewpage.getAdapter().notifyDataSetChanged();
                }
                break;
            case REQUESTCODE_FROM_MAIN_TO_PEPELU:
                if (resultCode == Activity.RESULT_CANCELED) {
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
}
