package com.xiaomai.yyshanghu.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.bean.PageDeviceListBean;

import java.util.List;

public class Adapter_Zaixian extends BaseQuickAdapter<PageDeviceListBean.DataBean.ListBean,BaseViewHolder> {
    public Adapter_Zaixian(int layoutResId, @Nullable List<PageDeviceListBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PageDeviceListBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_installreal_name,item.getId()+"");//设备号
        if(item.getLongitude().equals("")){
            helper.setText(R.id.tv_Latitude_and_longitude,"经纬度："+"暂无经纬度");//经纬度
        }else {
            helper.setText(R.id.tv_Latitude_and_longitude,"经纬度："+item.getLongitude()+"，"+item.getLatitude());//经纬度
        }
        helper.setText(R.id.tv_address,item.getBoxAddress()+"");//地址
        helper.setText(R.id.tv_slot,item.getStock()+"槽");//插槽数
        helper.setText(R.id.tv_price,item.getDetails()+"/小时");//价格
        helper.setText(R.id.item_zaixian_money,item.getSumProfit()+"");//累计收益
    }

//    @Override
//    protected void convert(BaseViewHolder helper, String item) {
//        helper.setText(R.id.item_zaixian_money,item);
//    }
}
