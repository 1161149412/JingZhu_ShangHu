package com.xiaomai.yyshanghu.details;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.base.BaseActivity;
import com.xiaomai.yyshanghu.bean.GetDeviceFreezeDetailBean;
import com.xiaomai.yyshanghu.net.RetrofitClient;
import com.xiaomai.yyshanghu.utils.DialogUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FreezeDetailsActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;

    String id;
    @BindView(R.id.tv_shebei_bianghao)
    TextView tv_shebei_bianghao;
    @BindView(R.id.tv_shanghu_name)
    TextView tv_shanghu_name;
    @BindView(R.id.tv_shebei_ztai)
    TextView tv_shebei_ztai;
    @BindView(R.id.tv_cyr)
    TextView tv_cyr;
    @BindView(R.id.tv_dongjie_jine)
    TextView tv_dongjie_jine;
    @BindView(R.id.tv_yjd_money)
    TextView tv_yjd_money;
    @BindView(R.id.tv_wei_jiedong)
    TextView tv_wei_jiedong;
    @BindView(R.id.tv_jiedong_sj)
    TextView tv_jiedong_sj;
    @BindView(R.id.tv_wei_jiedong_sj)
    TextView tv_wei_jiedong_sj;
    @BindView(R.id.tv_one_daili)
    TextView tv_one_daili;
    @BindView(R.id.tv_two_daili)
    TextView tv_two_daili;
    @BindView(R.id.tv_three_daili)
    TextView tv_three_daili;
    @BindView(R.id.line_oen)
    LinearLayout line_oen;
    @BindView(R.id.line_two)
    LinearLayout line_two;
    @BindView(R.id.line_three)
    LinearLayout line_three;
    @BindView(R.id.linear_layout_jiedong)
    LinearLayout linear_layout_jiedong;
    @BindView(R.id.linear_layout_hkuang)
    LinearLayout linear_layout_hkuang;
    @BindView(R.id.line_first)
    LinearLayout line_first;
    @BindView(R.id.tv_all_daili)
    TextView tv_all_daili;
    SharedPreferences usertoken;
    String token;
    Dialog dialog;
    @Override
    public int getLayoutId() {
        return R.layout.activity_freeze_details;
    }

    @Override
    public void initView() {

        usertoken= getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        getDeviceFreezeDetail(id);
    }

    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    private void getDeviceFreezeDetail(final String id) {
        dialog = DialogUtils.showDialog_progressbar(FreezeDetailsActivity.this);
        RetrofitClient.getInstance().getApi(token).GetDeviceFreezeDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetDeviceFreezeDetailBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetDeviceFreezeDetailBean>() {
                    @Override
                    public void accept(GetDeviceFreezeDetailBean bean) throws Exception {
                        tv_shebei_bianghao.setText(bean.getData().getId());
                        tv_shanghu_name.setText(bean.getData().getSellerName());
                        if(bean.getData().getIsFreeze().equals("1")){
                            tv_shebei_ztai.setText("冻结");//都没有
                            linear_layout_jiedong.setVisibility(View.GONE);
                            linear_layout_hkuang.setVisibility(View.GONE);
                        }else if(bean.getData().getIsFreeze().equals("2")){
                            tv_shebei_ztai.setText("已解冻");//解冻时间 只有
                            linear_layout_jiedong.setVisibility(View.VISIBLE);
                            linear_layout_hkuang.setVisibility(View.GONE);
                        }else if(bean.getData().getIsFreeze().equals("3")){
                            tv_shebei_ztai.setText("已回款");//都有
                            linear_layout_jiedong.setVisibility(View.VISIBLE);
                            linear_layout_hkuang.setVisibility(View.VISIBLE);
                        }
                        tv_cyr.setText(bean.getData().getSellerLinkman());
                        tv_dongjie_jine.setText(bean.getData().getFreezeMoney()+"元");
                        tv_yjd_money.setText(bean.getData().getUnfreezeMoney()+"元");
                        tv_wei_jiedong.setText(bean.getData().getResultFreezeMoney()+"元");
//                        tv_wei_jiedong.setText(getStrTime(bean.getData().getResultFreezeMoney())+"元");
                        tv_jiedong_sj.setText(bean.getData().getFreezeTime());
                        tv_wei_jiedong_sj.setText(getStrTime(bean.getData().getUpdateTime()));

                        if (bean.getData().getAgentTrees().size()==0){
                            line_first.setVisibility(View.GONE);
                            line_oen.setVisibility(View.GONE);
                            line_two.setVisibility(View.GONE);
                            line_three.setVisibility(View.GONE);
                        }

                       for (int i = 0;i<bean.getData().getAgentTrees().size();i++){
                        if (i==0){
                            tv_all_daili.setText("总代理："+bean.getData().getAgentTrees().get(0).getAgentName());
                            line_first.setVisibility(View.VISIBLE);
                            line_oen.setVisibility(View.GONE);
                            line_two.setVisibility(View.GONE);
                            line_three.setVisibility(View.GONE);
                        }else if(i==1){
                            tv_one_daili.setText("一级代理："+bean.getData().getAgentTrees().get(1).getAgentName());
                            line_oen.setVisibility(View.VISIBLE);
                            line_two.setVisibility(View.GONE);
                            line_three.setVisibility(View.GONE);
                        }else if(i==2){
                            tv_two_daili.setText("二级代理："+bean.getData().getAgentTrees().get(2).getAgentName());
                            line_two.setVisibility(View.VISIBLE);
                            line_three.setVisibility(View.GONE);
                        }else if(i==3){
                            line_three.setVisibility(View.VISIBLE);
                            tv_three_daili.setText("三级代理："+bean.getData().getAgentTrees().get(3).getAgentName());
                        }
                       }
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

    //时间戳转字符串
    public static String getStrTime(String timeStamp){
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long  l = Long.valueOf(timeStamp);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

}
