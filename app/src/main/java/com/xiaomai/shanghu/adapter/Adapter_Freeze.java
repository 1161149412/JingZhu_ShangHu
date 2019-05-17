package com.xiaomai.shanghu.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.bean.DeviceFreezeListBean;

import java.util.List;

public class Adapter_Freeze extends BaseQuickAdapter<DeviceFreezeListBean.DataBean.ListBean,BaseViewHolder> {
    public Adapter_Freeze(int layoutResId, @Nullable List<DeviceFreezeListBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceFreezeListBean.DataBean.ListBean item) {
        helper.setText(R.id.item_freeze_money,item.getFreezeMoney()+"");//冻结金额
        helper.setText(R.id.tv_sellerName,item.getSellerName()+"");//商户名
        helper.setText(R.id.tv_device_id,item.getId()+"");//设备id
        helper.setText(R.id.tv_user_name,"持有人:"+item.getSellerLinkman());//持有人
        helper.setText(R.id.tv_installreal_name,"安装人："+item.getInstallRealname()+"");//安装人
        helper.setText(R.id.tv_yjd_money,item.getUnfreezeMoney()+"");//已解冻金额
        helper.setText(R.id.tv_number,item.getStock()+"槽");
    }

//    @Override
//    protected void convert(BaseViewHolder helper, String item) {
//        helper.setText(R.id.item_freeze_money,item);
//    }
}
