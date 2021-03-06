package com.xiaomai.yyshanghu.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomai.yyshanghu.R;

import java.util.List;

/**
 * Created by EDZ on 2019/5/5.
 */

public class Adapter_Filter_TiXian extends BaseQuickAdapter<String,BaseViewHolder> {
    private int selectItem = 0;

    public Adapter_Filter_TiXian(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_filter_type_name,item);
        if (helper.getAdapterPosition()==selectItem){
            //选中
            helper.setBackgroundRes(R.id.item_filter_type_name, R.drawable.filter_button);
            helper.setTextColor(R.id.item_filter_type_name, Color.parseColor("#FFFFFF"));
        }else {
            //未选中
            helper.setBackgroundRes(R.id.item_filter_type_name, R.drawable.filter_button_no);
            helper.setTextColor(R.id.item_filter_type_name, Color.parseColor("#363636"));
        }
    }
    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }
}
