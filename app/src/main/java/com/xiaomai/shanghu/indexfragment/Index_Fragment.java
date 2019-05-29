package com.xiaomai.shanghu.indexfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;
import com.xiaomai.shanghu.LoginActivity;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.base.BaseFragment;
import com.xiaomai.shanghu.bean.AppUpdateBean;
import com.xiaomai.shanghu.bean.GetCodeBean;
import com.xiaomai.shanghu.bean.IndexBean;
import com.xiaomai.shanghu.freeze.FreezeActivity;
import com.xiaomai.shanghu.net.RetrofitClient;
import com.xiaomai.shanghu.tixian.PersonalInformationaActivity;
import com.xiaomai.shanghu.tixian.TiXianActivity;
import com.xiaomai.shanghu.tixian.TiXianJiLuActivity;
import com.xiaomai.shanghu.utils.AddDefaultScreenAreaUtils;
import com.xiaomai.shanghu.utils.AppUtils;
import com.xiaomai.shanghu.utils.DialogUtils;
import com.xiaomai.shanghu.utils.StringToIntUtils;
import com.xiaomai.shanghu.utils.ToastUtil;
import com.xiaomai.shanghu.utils.UpdateAppHttpUtil;
import com.xiaomai.shanghu.vipconfig.VipConfigActivity;

import org.json.JSONException;
import org.json.JSONObject;

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
    @BindView(R.id.index_device_allcount)
    TextView index_device_allcount;
    @BindView(R.id.personal_information)
    ImageView personal_information;
    @BindView(R.id.btn_callPhone)
    Button btn_callPhone;
    @BindView(R.id.bt_index_my_bill)
    LinearLayout bt_index_my_bill;
    @BindView(R.id.bt_index_vip_deploy)
    LinearLayout bt_index_vip_deploy;
    @BindView(R.id.bt_index_freeze_device)
    LinearLayout bt_index_freeze_device;
    @BindView(R.id.bt_index_personal_information)
    LinearLayout bt_index_personal_information;

    private SharedPreferences usertoken;
    private String token,appServerVersionCode;
    private Dialog dialog;
    private int appLocalVersionCode;
    @Override
    protected void initView(View view) {
        AddDefaultScreenAreaUtils.addDefaultScreenArea(personal_information,10,10,10,10);
        usertoken= getActivity().getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");
        appLocalVersionCode = AppUtils.getLocationCode(getActivity());//获取当前版本号
        dialog = DialogUtils.showDialog_progressbar(getContext());

        getData();
        appUpdate();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    @SuppressLint("ResourceType")
    @OnClick({R.id.bt_index_tixian, R.id.bt_index_jilu,R.id.personal_information,R.id.btn_callPhone,R.id.bt_index_my_bill,R.id.bt_index_vip_deploy,R.id.bt_index_freeze_device,R.id.bt_index_personal_information})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_index_tixian:
                //提现页面
                toClass(getActivity(), TiXianActivity.class);
                break;
            case R.id.bt_index_jilu:
                //提现记录
                break;
            case R.id.personal_information: //注销
                confirmOutLoginDialog();//注销
                break;
            case R.id.btn_callPhone:
                //拨打电话
                callPhone("02886666869");
                break;
            case R.id.bt_index_my_bill://提现记录
                toClass(getActivity(), TiXianJiLuActivity.class);
                break;
            case R.id.bt_index_vip_deploy://vip配置
                toClass(getActivity(), VipConfigActivity.class);
                break;
            case R.id.bt_index_freeze_device://冻结设备
                toClass(getActivity(),FreezeActivity.class);
                break;
            case R.id.bt_index_personal_information://个人资料
                toClass(getActivity(), PersonalInformationaActivity.class);//跳转个人资料
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
                            offLine.setText(bean.getData().getOffLineCount()+"");
                            onLine.setText(bean.getData().getOnLineCount()+"");
                            index_device_allcount.setText(bean.getData().getDeviceCount()+"");
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
     * APP更新
     * */
    private void appUpdate(){
        RetrofitClient.getInstance().getApi_AppUpdate().appUpdata("2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<AppUpdateBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<AppUpdateBean>() {
                               @Override
                               public void accept(AppUpdateBean appUpdateBean) throws Exception {
                                    appServerVersionCode = appUpdateBean.getNewVersion();

                                   Log.d("更新", "APP版本："+appLocalVersionCode+"  服务器版本："+appServerVersionCode);
                                   /**
                                    * 更新操作
                                    * */
                                   if(appLocalVersionCode < Integer.parseInt(appServerVersionCode)){
                                       appUpdateOperating(getActivity());
                                   }

                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.d("更新", "APP更新"+throwable);
                               }
                           }
                );
    }

    /**
     * APP更新操作
     * */

    private void appUpdateOperating(Activity activity){
        new UpdateAppManager.Builder()
                .setActivity(activity)
                .setUpdateUrl("https://www.jzcdsc.com/chargeAgent-0.0.1-SNAPSHOT/agentCenter/account/version/update?type=2")
                .setHttpManager(new UpdateAppHttpUtil())
                .setTopPic(R.mipmap.top_8)
                .build()
                .checkNewApp(new UpdateCallback() {
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        UpdateAppBean updateAppBean = new UpdateAppBean();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            updateAppBean
                                    //（必须）是否更新Yes,No
                                    .setUpdate(jsonObject.optString("udate"))
                                    //（必须）新版本号，
                                    .setNewVersion(jsonObject.optString("newVersion"))
                                    //（必须）下载地址
                                    .setApkFileUrl(jsonObject.optString("apkFileUrl"))
                                    //（必须）更新内容
                                    .setUpdateLog(jsonObject.optString("updateLog"))
                                    //大小，不设置不显示大小，可以不设置
                                    .setTargetSize(jsonObject.optString("targetSize"))
                                    //是否强制更新，可以不设置constraint
                                    .setConstraint(jsonObject.optBoolean("cons"))
                                    //设置md5，可以不设置
                                    .setNewMd5(jsonObject.optString("newMd5"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return updateAppBean;
                    }

                });
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
