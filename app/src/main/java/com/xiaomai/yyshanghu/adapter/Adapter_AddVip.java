package com.xiaomai.yyshanghu.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.bean.AddVipBean;

import java.util.List;

/**
 * Created by EDZ on 2019/5/28.
 */

public class Adapter_AddVip extends BaseQuickAdapter<AddVipBean.DataBean.ListBean,BaseViewHolder> {
    public Adapter_AddVip(int layoutResId, @Nullable List<AddVipBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddVipBean.DataBean.ListBean item) {
        helper.setText(R.id.item_tv_name,item.getUserName());//姓名
        helper.setText(R.id.item_tv_phone,item.getMobile()+"");//手机号
        helper.setText(R.id.item_tv_time,item.getResultFreeTime());//剩余时间
        helper.addOnClickListener(R.id.delete);
    }
}
