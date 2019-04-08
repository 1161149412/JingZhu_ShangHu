package com.xiaomai.shanghu.indexfragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.adapter.Adapter_Tixianzhong;
import com.xiaomai.shanghu.adapter.Adapter_ZhangDan;
import com.xiaomai.shanghu.base.BaseFragment;
import com.xiaomai.shanghu.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ZhangDan_Fragment extends BaseFragment {
    @BindView(R.id.tv_tixian_money)
    TextView tvTixianMoney;
    @BindView(R.id.tv_tixian_dongjie)
    TextView tvTixianDongjie;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;

    private List<String> list;
    private Adapter_ZhangDan adapter;

    @Override
    protected void initView(View view) {
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        list = new ArrayList<>();
        list.add("9527.00");
        list.add("9528.00");
        list.add("9528.00");
        list.add("9528.00");
        adapter = new Adapter_ZhangDan(R.layout.zhangdan_item, list);
        recycler.setAdapter(adapter);
        adapter.openLoadAnimation();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhangdan;
    }

}
