package com.xiaomai.shanghu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaomai.shanghu.base.BaseActivity;
import com.xiaomai.shanghu.indexfragment.Index_Fragment;
import com.xiaomai.shanghu.indexfragment.SheBei_Fragment;
import com.xiaomai.shanghu.indexfragment.ZhangDan_Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fragment_main)
    LinearLayout fragmentMain;
    @BindView(R.id.bt_index_index)
    RelativeLayout btIndexIndex;
    @BindView(R.id.bt_index_Zhangdan)
    RelativeLayout btIndexZhangdan;
    @BindView(R.id.bt_index_Shebei)
    RelativeLayout btIndexShebei;
    @BindView(R.id.index_icon)
    ImageView indexIcon;
    @BindView(R.id.index_name)
    TextView indexName;
    @BindView(R.id.zhangdan_icon)
    ImageView zhangdanIcon;
    @BindView(R.id.zhangdan_name)
    TextView zhangdanName;
    @BindView(R.id.shebei_icon)
    ImageView shebeiIcon;
    @BindView(R.id.shebei_name)
    TextView shebeiName;

    private Fragment fragment;
    private List<Fragment> fragmentList;
    private int currentIndex = 0;
    private FragmentManager fragmentManager;
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        fragment = new Fragment();
        fragmentList = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT, 0);
            fragmentList.removeAll(fragmentList);
            fragmentList.add(fragmentManager.findFragmentByTag(0 + ""));
            fragmentList.add(fragmentManager.findFragmentByTag(1 + ""));
            fragmentList.add(fragmentManager.findFragmentByTag(2 + ""));
            restoreFragment();
        } else {
            fragmentList.add(new Index_Fragment());
            fragmentList.add(new ZhangDan_Fragment());
            fragmentList.add(new SheBei_Fragment());
            showFragment();

        }
    }

    @Override

    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_FRAGMENT, currentIndex);
        super.onSaveInstanceState(outState);
    }

    private void showFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!fragmentList.get(currentIndex).isAdded()) {
            transaction
                    .hide(fragment)
                    .add(R.id.fragment_main, fragmentList.get(currentIndex), "" + currentIndex);
        } else {
            transaction
                    .hide(fragment)
                    .show(fragmentList.get(currentIndex));
        }
        fragment = fragmentList.get(currentIndex);
        transaction.commit();
    }


    private void restoreFragment() {
        FragmentTransaction mBeginTreansaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            if (i == currentIndex) {
                mBeginTreansaction.show(fragmentList.get(i));
            } else {
                mBeginTreansaction.hide(fragmentList.get(i));
            }
        }
        mBeginTreansaction.commit();
        fragment = fragmentList.get(currentIndex);

    }

    @OnClick({R.id.bt_index_index, R.id.bt_index_Zhangdan, R.id.bt_index_Shebei})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_index_index:
                currentIndex = 0;
                showFragment();
                indexName.setTextColor(getResources().getColor(R.color.appbar));
                zhangdanName.setTextColor(getResources().getColor(R.color.index_text_normal));
                shebeiName.setTextColor(getResources().getColor(R.color.index_text_normal));

                indexIcon.setImageResource(R.mipmap.index_home_hover);
                zhangdanIcon.setImageResource(R.mipmap.index_bill_nomal);
                shebeiIcon.setImageResource(R.mipmap.index_equipment_nomal);
                break;
            case R.id.bt_index_Zhangdan:
                currentIndex = 1;
                showFragment();

                indexName.setTextColor(getResources().getColor(R.color.index_text_normal));
                zhangdanName.setTextColor(getResources().getColor(R.color.appbar));
                shebeiName.setTextColor(getResources().getColor(R.color.index_text_normal));

                indexIcon.setImageResource(R.mipmap.index_home_nomal);
                zhangdanIcon.setImageResource(R.mipmap.index_bill_hover);
                shebeiIcon.setImageResource(R.mipmap.index_equipment_nomal);
                break;
            case R.id.bt_index_Shebei:
                currentIndex = 2;
                showFragment();

                indexName.setTextColor(getResources().getColor(R.color.index_text_normal));
                zhangdanName.setTextColor(getResources().getColor(R.color.index_text_normal));
                shebeiName.setTextColor(getResources().getColor(R.color.appbar));

                indexIcon.setImageResource(R.mipmap.index_home_nomal);
                zhangdanIcon.setImageResource(R.mipmap.index_bill_nomal);
                shebeiIcon.setImageResource(R.mipmap.index_equipment_hover);
                break;
        }
    }


}
