package com.xiaomai.shanghu.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.bean.DepositsBean;

import java.util.List;

public class Adapter_Yitixian extends BaseQuickAdapter<DepositsBean.DataBean.ListBean,BaseViewHolder> {
    public Adapter_Yitixian(int layoutResId, @Nullable List<DepositsBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DepositsBean.DataBean.ListBean item) {
        helper.setText(R.id.item_banktype,item.getBank());
        helper.setText(R.id.item_money,item.getMoney());
        helper.setText(R.id.item_time,item.getApplyTime());
        helper.setText(R.id.item_bankid,"尾号(8888)"+item.getRealName());
        helper.setText(R.id.item_tel,item.getMobile());
        helper.setText(R.id.item_orderid,item.getId());


    }
}
