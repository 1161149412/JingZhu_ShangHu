package com.xiaomai.shanghu.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomai.shanghu.R;

import java.util.List;

public class Adapter_Weitongguo extends BaseQuickAdapter<String,BaseViewHolder> {
    public Adapter_Weitongguo(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_fail,item);
    }
}
