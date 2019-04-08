package com.xiaomai.shanghu.indexfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.adapter.TabAdapter;
import com.xiaomai.shanghu.base.BaseFragment;
import com.xiaomai.shanghu.filter.Filter_SheBeiActivity;
import com.xiaomai.shanghu.frament_shebei.Fragment_LiXian;
import com.xiaomai.shanghu.frament_shebei.Fragment_ZaiXian;
import com.xiaomai.shanghu.freeze.FreezeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SheBei_Fragment extends BaseFragment {
    @BindView(R.id.tv_shebei_zaixian)
    TextView tvShebeiZaixian;
    @BindView(R.id.tv_shebei_lixian)
    TextView tvShebeiLixian;
    @BindView(R.id.bt_shebei_dongjie)
    TextView btShebeiDongjie;
    @BindView(R.id.xtab)
    XTabLayout xtab;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    Unbinder unbinder;
    @BindView(R.id.bt_filter)
    TextView btFilter;
    Unbinder unbinder1;

    private List<String> titleList;
    private List<Fragment> fragmentList;

    @Override
    protected void initView(View view) {
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
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shebei;
    }




    @OnClick({R.id.bt_filter, R.id.bt_shebei_dongjie})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_filter:
                toClass(getActivity(),Filter_SheBeiActivity.class);
                break;
            case R.id.bt_shebei_dongjie:
                toClass(getActivity(),FreezeActivity.class);
                break;
        }
    }
}
