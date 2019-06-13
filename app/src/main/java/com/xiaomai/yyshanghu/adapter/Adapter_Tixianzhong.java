package com.xiaomai.yyshanghu.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomai.yyshanghu.R;

import java.util.List;

public class Adapter_Tixianzhong extends BaseQuickAdapter<String,BaseViewHolder> {
    public Adapter_Tixianzhong(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_money,item);
    }
}
