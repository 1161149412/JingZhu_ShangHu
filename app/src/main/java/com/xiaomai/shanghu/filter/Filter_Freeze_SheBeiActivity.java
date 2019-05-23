package com.xiaomai.shanghu.filter;

import android.content.SharedPreferences;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.adapter.Adapter_Filter;
import com.xiaomai.shanghu.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Filter_Freeze_SheBeiActivity extends BaseActivity {


    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.filter_bt_reset)
    TextView filterBtReset;
    @BindView(R.id.filter_bt_submit)
    TextView filterBtSubmit;
    @BindView(R.id.edit_put_shebe_id)
    EditText edit_put_shebe_id;

    private List<String> list;
    private Adapter_Filter adapter;

    private SharedPreferences filter_freeze;
    private SharedPreferences.Editor editor_freeze;
    private String slot;
    @Override
    public int getLayoutId() {
        return R.layout.activity_filter_freeze_she_bei;
    }

    @Override
    public void initView() {
        filter_freeze = getSharedPreferences("filter_freeze", 0);
        editor_freeze = filter_freeze.edit();

        slot = "";

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
//                slot = adapter.getItem(position);
                if(position == 0){
                    slot ="";
                }else if(position == 1){
                    slot = 6+"";
                }else if(position == 2){
                    slot = 12+"";
                }
                adapter.notifyDataSetChanged();
            }
        });
    }


    @OnClick({R.id.back, R.id.filter_bt_reset, R.id.filter_bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.filter_bt_reset:
                filter_freeze.edit().clear().commit();
                edit_put_shebe_id.setText("");
                adapter.setSelectItem(0);
                slot = "";
                adapter.notifyDataSetChanged();
                break;
            case R.id.filter_bt_submit:

                String str = edit_put_shebe_id.getText().toString();
                editor_freeze.putString("deviceId",str);
                editor_freeze.putString("slot",slot);
                editor_freeze.commit();
                finish();
                break;
        }
    }


}
