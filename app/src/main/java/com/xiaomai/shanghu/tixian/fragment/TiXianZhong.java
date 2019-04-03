package com.xiaomai.shanghu.tixian.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.adapter.Adapter_Tixianzhong;
import com.xiaomai.shanghu.base.BaseFragment;
import com.xiaomai.shanghu.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TiXianZhong extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;

    private List<String> list;
    private Adapter_Tixianzhong adapter;

    @Override
    protected void initView(View view) {
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new SpacesItemDecoration(20));
        list = new ArrayList<>();
        for (int i = 8000; i < 8010; i++) {
            list.add(i + "");
        }
        adapter = new Adapter_Tixianzhong(R.layout.tixianzhong_item, list);
        recycler.setAdapter(adapter);
        adapter.openLoadAnimation();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.tixianzhong_fragment;
    }

}
