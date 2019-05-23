package com.xiaomai.shanghu.filter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.adapter.Adapter_Filter;
import com.xiaomai.shanghu.base.BaseActivity;
import com.xiaomai.shanghu.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    @BindView(R.id.edit_put_shebe_id)
    EditText edit_put_shebe_id;


    private List<String> list;
    private Adapter_Filter adapter;

    private SharedPreferences usertoken;
    private String token;
    private String slot;
    private SharedPreferencesUtil sharedPreferencesUtil;
    @Override
    public int getLayoutId() {
        return R.layout.activity_filter__she_bei;
    }

    @Override
    public void initView() {
        usertoken= getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");

        sharedPreferencesUtil = new SharedPreferencesUtil(this);

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

    @SuppressLint("ResourceAsColor")
    @OnClick({R.id.back, R.id.filter_bt_reset, R.id.filter_bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.filter_bt_reset:
                edit_put_shebe_id.setText("");
                adapter.setSelectItem(0);
                slot ="";
                adapter.notifyDataSetChanged();
                break;
            case R.id.filter_bt_submit:
                sharedPreferencesUtil.removeAll();
                String str = edit_put_shebe_id.getText().toString();

                sharedPreferencesUtil.putSP("deviceld",str+"");
                sharedPreferencesUtil.putSP("slot",slot+"");

                final String deviceld = sharedPreferencesUtil.getSP("deviceld");
                final String slot =  sharedPreferencesUtil.getSP("slot");
                Log.d("msg", "deviceld:"+deviceld);
                Log.d("msg", "slot:"+slot);
                Intent intent = new Intent();
                // 设置返回码和返回携带的数据
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
        }
    }

}
