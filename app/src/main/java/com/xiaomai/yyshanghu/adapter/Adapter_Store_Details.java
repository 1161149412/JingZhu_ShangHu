package com.xiaomai.yyshanghu.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomai.yyshanghu.R;

import java.util.List;

/**
 * Created by EDZ on 2019/6/11.
 */

public class Adapter_Store_Details extends BaseQuickAdapter<String,BaseViewHolder> {

    public Adapter_Store_Details(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_tv_yesterday_order_all_amount,item+"元");
    }
}
