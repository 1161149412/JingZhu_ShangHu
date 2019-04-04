package com.xiaomai.shanghu.filter;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.adapter.Adapter_Filter;
import com.xiaomai.shanghu.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Filter_SheBeiActivity extends BaseActivity {


    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.filter_bt_reset)
    TextView filterBtReset;
    @BindView(R.id.filter_bt_submit)
    TextView filterBtSubmit;

    private List<String> list;
    private Adapter_Filter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_filter__she_bei;
    }

    @Override
    public void initView() {
        recycler.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        list = new ArrayList<>();
        list.add("全部");
        list.add("6槽");
        list.add("12槽");
        adapter = new Adapter_Filter(R.layout.item_filter_type, list);
        recycler.setAdapter(adapter);
        /**
         * adapter.setOnItemChildClickListener
         * 这个点击无效
         * */
        adapter.openLoadAnimation();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
                adapter.setSelectItem(position);
                adapter.notifyDataSetChanged();
            }
        });
    }


    @OnClick({R.id.back, R.id.filter_bt_reset, R.id.filter_bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                break;
            case R.id.filter_bt_reset:
                break;
            case R.id.filter_bt_submit:
                break;
        }
    }
}
