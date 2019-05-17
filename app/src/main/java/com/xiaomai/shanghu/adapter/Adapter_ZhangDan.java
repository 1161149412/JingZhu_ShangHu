package com.xiaomai.shanghu.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.bean.GetPageSellerBillBean;

import java.util.List;

public class Adapter_ZhangDan extends BaseQuickAdapter<GetPageSellerBillBean.DataBean.ListBean,BaseViewHolder> {
    public Adapter_ZhangDan(int layoutResId, @Nullable List<GetPageSellerBillBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetPageSellerBillBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_title,""+item.getBillMonth()+"账单");//标题
        helper.setText(R.id.tv_account_period,"账期:"+item.getBillDate());//账期
        helper.setText(R.id.item_zhangdan_allmonet,item.getTotalPrice());//订单总额
        helper.setText(R.id.tv_total_income,item.getTotalEarn()+"");//总收益
        helper.setText(R.id.tv_out_bill,"出账日期:"+item.getCreateTime());//出账日期

    }

//    @Override
//    protected void convert(BaseViewHolder helper, String item) {
//        helper.setText(R.id.item_zhangdan_allmonet,item);
//    }
}
