package com.xiaomai.yyshanghu.indexfragment.storepage.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.adapter.Adapter_Store_Details;
import com.xiaomai.yyshanghu.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class StoreDetailsActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.img_msg)
    ImageView img_msg;
    @BindView(R.id.tv_all_income)
    TextView tv_all_income;
    @BindView(R.id.tv_month_income)
    TextView tv_month_income;
    @BindView(R.id.tv_yesterday_income)
    TextView tv_yesterday_income;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    private Adapter_Store_Details adapter;
    private List<String> list;

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_details;
    }

    @Override
    public void initView() {
        //配置recycleView
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layout);

        list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        adapter = new Adapter_Store_Details(R.layout.item_store_details, list);
        View inflate = LayoutInflater.from(this).inflate(R.layout.empty_view, null, false);
        adapter.setEmptyView(inflate);
        recycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                toClass(StoreDetailsActivity.this, StoreInformationActivity.class);
            }
        });


    }

    @OnClick({R.id.back, R.id.img_msg, R.id.tv_all_income, R.id.tv_month_income, R.id.tv_yesterday_income})
    public void onViewClicked(View view) {  //item_store_details 列表item
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.img_msg:
                break;
            case R.id.tv_all_income:
                break;
            case R.id.tv_month_income:
                break;
            case R.id.tv_yesterday_income:
                break;
        }
    }

}
