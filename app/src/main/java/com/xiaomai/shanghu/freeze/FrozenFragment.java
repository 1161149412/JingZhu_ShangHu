package com.xiaomai.shanghu.freeze;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.adapter.Adapter_Freeze;
import com.xiaomai.shanghu.adapter.Adapter_Laixian;
import com.xiaomai.shanghu.base.BaseFragment;
import com.xiaomai.shanghu.details.FreezeDetailsActivity;
import com.xiaomai.shanghu.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FrozenFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    private List<String> list;
    private Adapter_Freeze adapter;

    @Override
    protected void initView(View view) {
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new SpacesItemDecoration(10));
        list = new ArrayList<>();
        list.add("1111");
        list.add("1112");
        list.add("1113");
        adapter = new Adapter_Freeze(R.layout.item_freeze, list);
        recycler.setAdapter(adapter);
        adapter.openLoadAnimation();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                toClass(view.getContext(),FreezeDetailsActivity.class);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.freeze_fragment;
    }


}
