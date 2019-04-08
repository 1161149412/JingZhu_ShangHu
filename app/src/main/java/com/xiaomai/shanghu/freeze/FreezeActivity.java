package com.xiaomai.shanghu.freeze;

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
import com.xiaomai.shanghu.filter.Filter_SheBeiActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FreezeActivity extends BaseActivity {


    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.xtab)
    XTabLayout xtab;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.bt_filter)
    TextView btFilter;

    private List<String> titleList;
    private List<Fragment> fragmentList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_freeze;
    }

    @Override
    public void initView() {
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
    }


    @OnClick({R.id.back, R.id.bt_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bt_filter:
                toClass(this, Filter_SheBeiActivity.class);
                break;
        }
    }
}
