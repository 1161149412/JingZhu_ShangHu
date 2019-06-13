package com.xiaomai.yyshanghu.filter;

import android.content.SharedPreferences;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.adapter.Adapter_Filter_TiXian;
import com.xiaomai.yyshanghu.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Filter_TixianActivity extends BaseActivity {


    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.filter_bt_reset)
    TextView filterBtReset;
    @BindView(R.id.filter_bt_submit)
    TextView filterBtSubmit;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    Adapter_Filter_TiXian adapter_filter_tiXian;
    private List<String> list;
    private String state;
    private SharedPreferences filter_record;
    private SharedPreferences.Editor editor_record;

    @Override
    public int getLayoutId() {
        return R.layout.activity_filter__tixian;
    }

    @Override
    public void initView() {
        filter_record = getSharedPreferences("filter_record", 0);
        editor_record = filter_record.edit();

        recycler.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        list = new ArrayList<>();
        list.add("申请中");
        list.add("已通过");
        list.add("未通过");
        adapter_filter_tiXian = new Adapter_Filter_TiXian(R.layout.item_filter_type, list);
        recycler.setAdapter(adapter_filter_tiXian);
        /**
         * adapter.setOnItemChildClickListener
         * 这个点击无效
         * */
        adapter_filter_tiXian.openLoadAnimation();
        adapter_filter_tiXian.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
                adapter_filter_tiXian.setSelectItem(position);
                if(position == 0){
                    state =0+"";
                }else if(position == 1){
                    state = 1+"";
                }else if(position == 2){
                    state = -1+"";
                }
                adapter_filter_tiXian.notifyDataSetChanged();
            }
        });
    }


    @OnClick({R.id.back,R.id.filter_bt_reset, R.id.filter_bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.filter_bt_reset:
                filter_record.edit().clear().commit();
                adapter_filter_tiXian.setSelectItem(0);
                adapter_filter_tiXian.notifyDataSetChanged();
                break;
            case R.id.filter_bt_submit:
                editor_record.putString("state",state);
                editor_record.commit();

                finish();
                break;
        }
    }

}
