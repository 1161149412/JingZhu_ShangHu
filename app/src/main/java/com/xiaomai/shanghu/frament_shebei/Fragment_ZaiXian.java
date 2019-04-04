package com.xiaomai.shanghu.frament_shebei;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.adapter.Adapter_Tixianzhong;
import com.xiaomai.shanghu.adapter.Adapter_Zaixian;
import com.xiaomai.shanghu.base.BaseFragment;
import com.xiaomai.shanghu.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment_ZaiXian extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;

    private List<String> list;
    private Adapter_Zaixian adapter;

    @Override
    protected void initView(View view) {
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new SpacesItemDecoration(12));
        list=new ArrayList<>();
        list.add("20058.3");
        list.add("20058.3");
        list.add("20058.3");
        adapter=new Adapter_Zaixian(R.layout.item_shebei_zaixian,list);
        recycler.setAdapter(adapter);
        adapter.openLoadAnimation();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.shebei_zaixian;
    }


}
