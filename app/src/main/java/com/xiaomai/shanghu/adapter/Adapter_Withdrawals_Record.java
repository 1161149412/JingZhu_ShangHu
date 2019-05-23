package com.xiaomai.shanghu.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.bean.DepositsBean;
import com.xiaomai.shanghu.utils.StringToIntUtils;

import java.util.List;

/**
 * Created by EDZ on 2019/5/8.
 */

public class Adapter_Withdrawals_Record extends BaseQuickAdapter<DepositsBean.DataBean.ListBean,BaseViewHolder> {

    public Adapter_Withdrawals_Record(int layoutResId, @Nullable List<DepositsBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, DepositsBean.DataBean.ListBean item) {
        String str = item.getCreditCard();

        helper.setText(R.id.item_banktype,item.getBank());
        helper.setText(R.id.item_money,item.getMoney());
        helper.setText(R.id.item_time,item.getApplyTime());

        if (!TextUtils.isEmpty(str) && str.length()>=4){
            helper.setText(R.id.item_bankid,"尾号("+str.substring(str.length()-4,str.length())+")"+item.getRealName());
        }
        if(item.getServiceMoney().equals("")){
            helper.setText(R.id.item_tixan_sxf, "0.00"+"元");//提现手续费
        }else {
            helper.setText(R.id.item_tixan_sxf, StringToIntUtils.StringToInt(item.getServiceMoney())+"元");//提现手续费
        }

        TextView item_tag = helper.getView(R.id.item_tag);
        RelativeLayout relative_layout = helper.getView(R.id.relative_layout);
        LinearLayout linear_layout = helper.getView(R.id.linear_layout);
        if(item.getState().equals("-1")){
            relative_layout.setVisibility(View.VISIBLE);
            linear_layout.setVisibility(View.VISIBLE);
            helper.setText(R.id.item_fail,item.getDetails());
            item_tag.setText("未通过");
        }else if(item.getState().equals("1")){
            relative_layout.setVisibility(View.GONE);
            linear_layout.setVisibility(View.GONE);
            item_tag.setText("已通过");
        }else if(item.getState().equals("0")){
            relative_layout.setVisibility(View.GONE);
            linear_layout.setVisibility(View.GONE);
            item_tag.setText("申请中");
        }

    }
}
