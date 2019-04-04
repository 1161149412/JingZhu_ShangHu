package com.xiaomai.shanghu.tixian;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.adapter.TabAdapter;
import com.xiaomai.shanghu.base.BaseActivity;
import com.xiaomai.shanghu.filter.Filter_TixianActivity;
import com.xiaomai.shanghu.tixian.fragment.TiXianZhong;
import com.xiaomai.shanghu.tixian.fragment.WeiTongGuo;
import com.xiaomai.shanghu.tixian.fragment.YiTiXian;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TiXianJiLuActivity extends BaseActivity {


    @BindView(R.id.xtab)
    XTabLayout xtab;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.bt_filter)
    TextView btFilter;

    private List<String> titleList;
    private List<Fragment> fragmentList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ti_xian_ji_lu;
    }

    @Override
    public void initView() {
        titleList = new ArrayList<>();
        titleList.add("提现中");
        titleList.add("已提现");
        titleList.add("未通过");
        fragmentList = new ArrayList<>();
        fragmentList.add(new TiXianZhong());
        fragmentList.add(new YiTiXian());
        fragmentList.add(new WeiTongGuo());

        viewpage.setAdapter(new TabAdapter(getSupportFragmentManager(), titleList, fragmentList));
        viewpage.setOffscreenPageLimit(0);
        xtab.setupWithViewPager(viewpage);
        xtab.getTabAt(0).select();
        xtab.getTabAt(1).select();
        viewpage.setCurrentItem(0);

    }




    @OnClick({R.id.back, R.id.bt_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                break;
            case R.id.bt_filter:
                toClass(this,Filter_TixianActivity.class);
                break;
        }
    }
}
