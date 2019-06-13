package com.xiaomai.yyshanghu.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.bean.DepositsBean;

import java.util.List;

public class Adapter_Weitongguo extends BaseQuickAdapter<DepositsBean.DataBean.ListBean,BaseViewHolder> {
    public Adapter_Weitongguo(int layoutResId, @Nullable List<DepositsBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DepositsBean.DataBean.ListBean item) {
        String str = item.getCreditCard();

        helper.setText(R.id.item_banktype,item.getBank());
        helper.setText(R.id.item_money,item.getMoney());
        helper.setText(R.id.item_time,item.getApplyTime());
        if (!TextUtils.isEmpty(str) && str.length()>=4){
            helper.setText(R.id.item_bankid,"尾号("+str.substring(str.length()-4,str.length())+")"+item.getRealName());
        }
        helper.setText(R.id.item_tel,item.getMobile());
        helper.setText(R.id.item_orderid,item.getId());
        helper.setText(R.id.item_fail,item.getDetails());
    }

}
