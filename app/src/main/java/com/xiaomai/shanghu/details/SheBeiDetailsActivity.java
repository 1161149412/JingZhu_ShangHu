package com.xiaomai.shanghu.details;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.base.BaseActivity;
import com.xiaomai.shanghu.bean.GetDeviceDetailBean;
import com.xiaomai.shanghu.net.RetrofitClient;
import com.xiaomai.shanghu.utils.DialogUtils;
import com.xiaomai.shanghu.utils.StringToIntUtils;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SheBeiDetailsActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.tv_shebei_bianghao)
    TextView tv_shebei_bianghao;
    @BindView(R.id.tv_Latitude_and_longitude)
    TextView tv_Latitude_and_longitude;
    @BindView(R.id.tv_suozai_diqu)
    TextView tv_suozai_diqu;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_slot)
    TextView tv_slot;
    @BindView(R.id.tv_dongjie_jine)
    TextView tv_dongjie_jine;
    @BindView(R.id.tv_yjiedong)
    TextView tv_yjiedong;
    @BindView(R.id.tv_shanghu_name)
    TextView tv_shanghu_name;
    @BindView(R.id.tv_lxr)
    TextView tv_lxr;
    @BindView(R.id.tv_liangxi_fangshi)
    TextView tv_liangxi_fangshi;
    @BindView(R.id.tv_bangding_shijian)
    TextView tv_bangding_shijian;
    @BindView(R.id.tv_anzhuang_name)
    TextView tv_anzhuang_name;
    @BindView(R.id.tv_anzhuang_lxr)
    TextView tv_anzhuang_lxr;
    @BindView(R.id.tv_anzhuang_sj)
    TextView tv_anzhuang_sj;
    @BindView(R.id.tv_anzhuang_bangding_sj)
    TextView tv_anzhuang_bangding_sj;
    @BindView(R.id.img_lixian)
    ImageView img_lixian;
    @BindView(R.id.tv_lixian)
    TextView tv_lixian;
    @BindView(R.id.img_zaixian)
    ImageView img_zaixian;
    @BindView(R.id.tv_zaixian)
    TextView tv_zaixian;
    @BindView(R.id.tv_lixian_time)
    TextView tv_lixian_time;
    @BindView(R.id.tv_zong_yingli)
    TextView tv_zong_yingli;
    @BindView(R.id.tv_ben_yue_yinli)
    TextView tv_ben_yue_yinli;
    @BindView(R.id.tv_yue_pingju_yingli)
    TextView tv_yue_pingju_yingli;
    @BindView(R.id.tv_wei_wo_zhuang_qu)
    TextView tv_wei_wo_zhuang_qu;
    @BindView(R.id.tv_fengcheng)
    TextView tv_fengcheng;
    @BindView(R.id.tv_all)
    TextView tv_all;
    String id;

    SharedPreferences usertoken;
    String token;
    Dialog dialog;
    @Override
    public int getLayoutId() {
        return R.layout.activity_she_bei_details;
    }

    @Override
    public void initView() {
        usertoken= getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");

         Intent intent =getIntent();
         id=intent.getStringExtra("id");
         getDeviceDetail(id);
    }

    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    public void getDeviceDetail(String id){
        dialog = DialogUtils.showDialog_progressbar(SheBeiDetailsActivity.this);
        RetrofitClient.getInstance().getApi(token).GetDeviceDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetDeviceDetailBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetDeviceDetailBean>() {
                    @Override
                    public void accept(GetDeviceDetailBean bean) throws Exception {
                        tv_shebei_bianghao.setText(bean.getData().getId());

                        if(bean.getData().getLongitude().equals("")){
                            tv_Latitude_and_longitude.setText("暂无经纬度");
                        }else {
                            tv_Latitude_and_longitude.setText(bean.getData().getLongitude()+","+bean.getData().getLatitude());
                        }
                        tv_suozai_diqu.setText(bean.getData().getBoxAddress());
                        tv_address.setText(bean.getData().getBoxAddress());
                        tv_price.setText(bean.getData().getDetails()+"元/小时");
                        tv_slot.setText(bean.getData().getStock()+"槽");
                        tv_dongjie_jine.setText(bean.getData().getFreezeMoney()+"元");
                        tv_yjiedong.setText(bean.getData().getUnfreezeMoney()+"元");
                        tv_shanghu_name.setText(bean.getData().getSellerName());
                        tv_fengcheng.setText(bean.getData().getSellerReward());
                        tv_lxr.setText(bean.getData().getSellerLinkman());
                        tv_liangxi_fangshi.setText(bean.getData().getSellerLinktel());
                        tv_bangding_shijian.setText(StringToIntUtils.stringSubString(bean.getData().getCreateTime()));//绑定时间
                        tv_anzhuang_name.setText(bean.getData().getInstallRealname());
                        tv_anzhuang_lxr.setText(bean.getData().getInstallMobile());
                        tv_anzhuang_sj.setText(StringToIntUtils.stringSubString(bean.getData().getCreateTime()));
//                        tv_anzhuang_bangding_sj.setText(bean.getData().getInstallTime());

                        if(bean.getData().getState().equals("0")){
                            img_zaixian.setVisibility(View.GONE);
                            tv_zaixian.setVisibility(View.GONE);
                            tv_lixian_time.setText(bean.getData().getOfflineTime());
                        }else if(bean.getData().getState().equals("1")){
                            img_lixian.setVisibility(View.GONE);
                            tv_lixian.setVisibility(View.GONE);
                            tv_lixian_time.setVisibility(View.GONE);
                        }
                        tv_zong_yingli.setText(bean.getData().getSumProfit());
                        tv_ben_yue_yinli.setText(bean.getData().getMonthProfit());
                        tv_yue_pingju_yingli.setText(bean.getData().getAvgMonthProfit());
                        tv_wei_wo_zhuang_qu.setText(bean.getData().getEarnForMeSum());
                        tv_all.setText(bean.getData().getOrderCount());
                        DialogUtils.closeDialog(dialog);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("tag", "请求错误");
                        DialogUtils.closeDialog(dialog);
                    }
                });
    }


}
