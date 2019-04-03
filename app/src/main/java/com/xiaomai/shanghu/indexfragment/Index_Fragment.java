package com.xiaomai.shanghu.indexfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.base.BaseFragment;
import com.xiaomai.shanghu.tixian.TiXianActivity;
import com.xiaomai.shanghu.tixian.TiXianJiLuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Index_Fragment extends BaseFragment {
    @BindView(R.id.bt_index_icon)
    ImageView btIndexIcon;
    @BindView(R.id.tx_index_money)
    TextView txIndexMoney;
    @BindView(R.id.bt_index_tixian)
    TextView btIndexTixian;
    @BindView(R.id.bt_index_jilu)
    TextView btIndexJilu;
    @BindView(R.id.bt_index_zhuxiao)
    TextView btIndexZhuxiao;
    Unbinder unbinder;

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }


    @OnClick({R.id.bt_index_tixian, R.id.bt_index_jilu, R.id.bt_index_zhuxiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_index_tixian:
                toClass(getActivity(), TiXianActivity.class);
                break;
            case R.id.bt_index_jilu:
                toClass(getActivity(), TiXianJiLuActivity.class);
                break;
            case R.id.bt_index_zhuxiao:
                break;
        }
    }
}
