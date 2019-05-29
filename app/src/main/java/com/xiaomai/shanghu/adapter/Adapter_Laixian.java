package com.xiaomai.shanghu.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.bean.PageDeviceListBean;

import java.util.List;

public class Adapter_Laixian extends BaseQuickAdapter<PageDeviceListBean.DataBean.ListBean,BaseViewHolder> {
    public Adapter_Laixian(int layoutResId, @Nullable List<PageDeviceListBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PageDeviceListBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_installreal_name,item.getId()+"");//设备号
//        helper.setText(R.id.tv_Latitude_and_longitude,"经纬度："+item.getLongitude()+"，"+item.getLatitude());//经纬度
        if(item.getLongitude().equals("")){
            helper.setText(R.id.tv_Latitude_and_longitude,"经纬度："+"暂无经纬度");//经纬度
        }else {
            helper.setText(R.id.tv_Latitude_and_longitude,"经纬度："+item.getLongitude()+"，"+item.getLatitude());//经纬度
        }
        helper.setText(R.id.tv_address,item.getBoxAddress()+"");//地址
        helper.setText(R.id.tv_slot,item.getStock()+"槽");//插槽数
        helper.setText(R.id.tv_price,item.getDetails()+"/小时");//价格
        helper.setText(R.id.item_lixian_money,item.getSumProfit()+"");//累计收益
        helper.setText(R.id.tv_lixian_time,"离线\n"+item.getOfflineTime());//离线时长
    }

}
