package com.xiaomai.shanghu.indexfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xiaomai.shanghu.LoginActivity;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.base.BaseFragment;
import com.xiaomai.shanghu.bean.GetCodeBean;
import com.xiaomai.shanghu.bean.IndexBean;
import com.xiaomai.shanghu.net.RetrofitClient;
import com.xiaomai.shanghu.tixian.PersonalInformationaActivity;
import com.xiaomai.shanghu.tixian.TiXianActivity;
import com.xiaomai.shanghu.tixian.TiXianJiLuActivity;
import com.xiaomai.shanghu.utils.AddDefaultScreenAreaUtils;
import com.xiaomai.shanghu.utils.DialogUtils;
import com.xiaomai.shanghu.utils.StringToIntUtils;
import com.xiaomai.shanghu.utils.ToastUtil;

import butterknife.BindView;
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
    @BindView(R.id.personal_information)
    ImageView personal_information;
    @BindView(R.id.btn_callPhone)
    Button btn_callPhone;

    private SharedPreferences usertoken;
    private String token;
    private Dialog dialog;
    @Override
    protected void initView(View view) {
        AddDefaultScreenAreaUtils.addDefaultScreenArea(personal_information,10,10,10,10);
        usertoken= getActivity().getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");
        dialog = DialogUtils.showDialog_progressbar(getContext());
        getData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    @OnClick({R.id.bt_index_tixian, R.id.bt_index_jilu, R.id.bt_index_zhuxiao,R.id.personal_information,R.id.btn_callPhone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_index_tixian:
                //提现页面
                toClass(getActivity(), TiXianActivity.class);
                break;
            case R.id.bt_index_jilu:
                //提现记录
                toClass(getActivity(), TiXianJiLuActivity.class);
                break;
            case R.id.bt_index_zhuxiao:
                //注销
                confirmOutLoginDialog();
                break;
            case R.id.personal_information:
                //个人资料
                toClass(getActivity(), PersonalInformationaActivity.class);
                break;
            case R.id.btn_callPhone:
                //拨打电话
                callPhone("02886666869");
                break;
        }
    }

    /**
     * 获取商户信息
     * */
    private void getData() {

        RetrofitClient.getInstance().getApi(token).getIndexBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<IndexBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<IndexBean>() {
                    @Override
                    public void accept(IndexBean bean) throws Exception {
                        Log.d("tag", "请求成功");
                        if (bean.getCode()==1){
                            yesterdayMoney.setText(StringToIntUtils.StringToInt(bean.getData().getYestoday_earn()));
                            allMoney.setText(StringToIntUtils.StringToInt(bean.getData().getTotal_earn()));
                            monthMoney.setText(StringToIntUtils.StringToInt(bean.getData().getMonth_earn()));
                            todayMoney.setText(StringToIntUtils.StringToInt(bean.getData().getDay_earn())+"");
                            txIndexMoney.setText(StringToIntUtils.StringToInt(bean.getData().getUnliquidated()));
                            depositMoney.setText("已提现金额:"+StringToIntUtils.StringToInt(bean.getData().getLiquidated())+"元");
                            rent.setText("待租借:"+bean.getData().getRentCount()+"个");
                            rentting.setText("租借中:"+bean.getData().getNoRentCount()+"个");
                            offLine.setText("离线:"+bean.getData().getOffLineCount()+"台");
                            onLine.setText("在线:"+bean.getData().getOnLineCount()+"台");
                        }else if(bean.getCode()==-10 || bean.getMsg().equals("您的账户已在其他设备上登录")){
                            signOutDialog();
                            usertoken.edit().clear().commit();
                        }
                        DialogUtils.closeDialog(dialog);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.closeDialog(dialog);
                        if (throwable.getMessage().equals("HTTP 401 ")){
                            signOutDialog();
                            usertoken.edit().clear().commit();
                        }
                        Log.d("tag", "请求错误"+throwable.getMessage());
                    }
                });
    }

    /***
     * 退出登录
     * */
    public void LoginOut(){
        RetrofitClient.getInstance().getApi(token).LoginOut()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetCodeBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetCodeBean>() {
                               @Override
                               public void accept(GetCodeBean getCodeBean) throws Exception {
                                   if(getCodeBean.getCode()==1){
                                       ToastUtil.showShortToast(""+getCodeBean.getData().toString());
                                       SharedPreferences usertoken = getActivity().getSharedPreferences("mytoken", 0);
                                       usertoken.edit().clear().commit();
                                       toClass(getContext(), LoginActivity.class);
                                       getActivity().finish();
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.d("tag", "请求错误");
                               }
                           }
                );
    }

    /**
     * 拨打电话
     * */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    private void confirmOutLoginDialog(){
        final AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.confirm_outlogin_dialong, null, false);
        dialog.setView(view);
        dialog.setCancelable(false);
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginOut();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
}
