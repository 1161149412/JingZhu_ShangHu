package com.xiaomai.shanghu.indexfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.base.BaseFragment;
import com.xiaomai.shanghu.bean.IndexBean;
import com.xiaomai.shanghu.net.RetrofitClient;
import com.xiaomai.shanghu.tixian.TiXianActivity;
import com.xiaomai.shanghu.tixian.TiXianJiLuActivity;

import org.reactivestreams.Subscription;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Index_Fragment extends BaseFragment {
    @BindView(R.id.bt_index_icon)
    ImageView btIndexIcon;
    @BindView(R.id.tx_index_money)
    TextView txIndexMoney;
    @BindView(R.id.bt_index_tixian)
    TextView btIndexTixian;
    @BindView(R.id.bt_index_jilu)
    TextView btIndexJilu;
    @BindView(R.id.bt_index_zhuxiao)
    TextView btIndexZhuxiao;
    Unbinder unbinder;
    @BindView(R.id.yesterday_money)
    TextView yesterdayMoney;
    @BindView(R.id.all_money)
    TextView allMoney;
    @BindView(R.id.month_money)
    TextView monthMoney;
    @BindView(R.id.today_money)
    TextView todayMoney;
    @BindView(R.id.deposit_money)
    TextView depositMoney;
    @BindView(R.id.rent)
    TextView rent;
    @BindView(R.id.rentting)
    TextView rentting;
    @BindView(R.id.off_line)
    TextView offLine;
    @BindView(R.id.on_line)
    TextView onLine;
    Unbinder unbinder1;

    @Override
    protected void initView(View view) {
        getData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }


    @OnClick({R.id.bt_index_tixian, R.id.bt_index_jilu, R.id.bt_index_zhuxiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_index_tixian:
                toClass(getActivity(), TiXianActivity.class);
                break;
            case R.id.bt_index_jilu:
                toClass(getActivity(), TiXianJiLuActivity.class);
                break;
            case R.id.bt_index_zhuxiao:
                break;
        }
    }

    private void getData() {
        Subscription subscription = (Subscription) RetrofitClient.getInstance().getApi().getIndexBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<IndexBean>() {
                    @Override
                    public void accept(IndexBean bean) throws Exception {
                        Log.d("tag", "请求成功");
                        if (bean.getCode()==1){
                            yesterdayMoney.setText(bean.getData().getYestoday_earn());
                            allMoney.setText(bean.getData().getTotal_earn());
                            monthMoney.setText(bean.getData().getMonth_earn());
                            todayMoney.setText(bean.getData().getDay_earn());
                            txIndexMoney.setText(bean.getData().getUnliquidated());
                            depositMoney.setText("已提现金额:"+bean.getData().getLiquidated()+"元");
                            rent.setText("待租借:"+bean.getData().getNoRentCount()+"个");
                            rentting.setText("租借中:"+bean.getData().getRentCount()+"个");
                            offLine.setText("离线:"+bean.getData().getOffLineCount()+"台");
                            onLine.setText("在线:"+bean.getData().getOnLineCount()+"台");

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("tag", "请求错误");
                    }
                });
    }

}
