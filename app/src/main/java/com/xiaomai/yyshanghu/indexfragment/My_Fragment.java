package com.xiaomai.yyshanghu.indexfragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xiaomai.yyshanghu.LoginActivity;
import com.xiaomai.yyshanghu.R;
import com.xiaomai.yyshanghu.base.BaseFragment;
import com.xiaomai.yyshanghu.bean.GetCodeBean;
import com.xiaomai.yyshanghu.bean.GetDviceCountBean;
import com.xiaomai.yyshanghu.filter.Filter_SheBeiActivity;
import com.xiaomai.yyshanghu.freeze.FreezeActivity;
import com.xiaomai.yyshanghu.indexfragment.mypage.BankCardManagementActivity;
import com.xiaomai.yyshanghu.indexfragment.mypage.CertificationInfoActivity;
import com.xiaomai.yyshanghu.indexfragment.mypage.Staff_MangementActivity;
import com.xiaomai.yyshanghu.indexfragment.mypage.UpdateTelActivity;
import com.xiaomai.yyshanghu.net.RetrofitClient;
import com.xiaomai.yyshanghu.tixian.TiXianJiLuActivity;
import com.xiaomai.yyshanghu.utils.AddDefaultScreenAreaUtils;
import com.xiaomai.yyshanghu.utils.DialogUtils;
import com.xiaomai.yyshanghu.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class My_Fragment extends BaseFragment {

    @BindView(R.id.tv_zaixian)
    TextView tv_shebei_zaixian;
    @BindView(R.id.tv_lixian)
    TextView tv_shebei_lixian;
    @BindView(R.id.bt_shebei_dongjie)
    TextView btShebeiDongjie;
    @BindView(R.id.xtab)
    XTabLayout xtab;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.bt_filter)
    TextView btFilter;
    @BindView(R.id.img_circle)
    ImageView img_circle;
    @BindView(R.id.image_update_tel)
    ImageView image_update_tel;
    @BindView(R.id.bt_out_login)
    Button bt_out_login;
    @BindView(R.id.linear_layout_information)
    LinearLayout linear_layout_information;
    @BindView(R.id.linear_layout_withdraw_recording)
    LinearLayout linear_layout_withdraw_recording;
    @BindView(R.id.linear_layout_my_bill)
    LinearLayout linear_layout_my_bill;
    @BindView(R.id.linear_layout_staff_management)
    LinearLayout linear_layout_staff_management;
    @BindView(R.id.linear_layout_bank_card_management)
    LinearLayout linear_layout_bank_card_management;

    public final int REQUESTCODE_FROM_MAIN_TO_OTHER = 1;
    public final int REQUESTCODE_FROM_MAIN_TO_PEPELU = 2;

    private List<String> titleList;
    private List<Fragment> fragmentList;
    private SharedPreferences usertoken;
    private String token;
    private Dialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {
        AddDefaultScreenAreaUtils.addDefaultScreenArea(btFilter, 10, 10, 30, 10);//增加点击范围
        usertoken = getActivity().getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token", "0");
//        dialog = DialogUtils.showDialog_progressbar(getContext());

//        titleList = new ArrayList<>();
//        titleList.add("在线设备");
//        titleList.add("离线设备");
//        fragmentList = new ArrayList<>();
//        fragmentList.add(new Fragment_ZaiXian());
//        fragmentList.add(new Fragment_LiXian());
//
//        viewpage.setAdapter(new TabAdapter(getActivity().getSupportFragmentManager(), titleList, fragmentList));
//        viewpage.setOffscreenPageLimit(0);
//        xtab.setupWithViewPager(viewpage);
//        xtab.getTabAt(0).select();
//        xtab.getTabAt(1).select();
//        viewpage.setCurrentItem(0);

//        getData();

        /**
         * 加载圆形头像控件
         * */
        Glide.with(this)
                .load("http://img5.duitang.com/uploads/item/201506/07/20150607110911_kY5cP.jpeg")
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(img_circle);

    }

    @OnClick({R.id.bt_filter, R.id.bt_shebei_dongjie,R.id.bt_out_login,R.id.image_update_tel, R.id.linear_layout_information, R.id.linear_layout_withdraw_recording, R.id.linear_layout_my_bill, R.id.linear_layout_staff_management, R.id.linear_layout_bank_card_management})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_filter:
                Intent intent = new Intent(getActivity(), Filter_SheBeiActivity.class);
                startActivityForResult(intent, REQUESTCODE_FROM_MAIN_TO_OTHER);
//                toClass(getActivity(),Filter_SheBeiActivity.class);
                break;
            case R.id.bt_shebei_dongjie:
                toClass(getActivity(), FreezeActivity.class);
                break;
            case R.id.linear_layout_information:
                toClass(getActivity(), CertificationInfoActivity.class);
                break;
            case R.id.linear_layout_withdraw_recording://提现记录
                toClass(getActivity(), TiXianJiLuActivity.class);
                break;
            case R.id.linear_layout_my_bill://我的账单
                break;
            case R.id.linear_layout_staff_management://员工管理
                toClass(getActivity(), Staff_MangementActivity.class);
                break;
            case R.id.linear_layout_bank_card_management://银行卡管理
                toClass(getActivity(), BankCardManagementActivity.class);
                break;
            case R.id.bt_out_login://退出
                confirmOutLoginDialog();
                break;
            case R.id.image_update_tel://更改手机号
                toClass(getActivity(), UpdateTelActivity.class);
                break;
        }
    }

    public void getData() {

        RetrofitClient.getInstance().getApi(token).getDeviceCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetDviceCountBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetDviceCountBean>() {
                    @Override
                    public void accept(GetDviceCountBean getDviceCountBean) throws Exception {
                        if (getDviceCountBean.getCode() == 1) {
                            tv_shebei_zaixian.setText(getDviceCountBean.getData().getOnline() + "");
                            tv_shebei_lixian.setText(getDviceCountBean.getData().getOffline() + "");
                        } else if (getDviceCountBean.getCode() == -10 || getDviceCountBean.getMsg().equals("您的账户已在其他设备上登录")) {
                            signOutDialog();
                            usertoken.edit().clear().commit();
                        }
                        DialogUtils.closeDialog(dialog);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("tag", "请求错误");
                        DialogUtils.closeDialog(dialog);
                        if (throwable.getMessage().equals("HTTP 401 ")) {
                            signOutDialog();
                            usertoken.edit().clear().commit();
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUESTCODE_FROM_MAIN_TO_OTHER:
                if (resultCode == Activity.RESULT_OK) {
                    viewpage.getAdapter().notifyDataSetChanged();
                }
                break;
            case REQUESTCODE_FROM_MAIN_TO_PEPELU:
                if (resultCode == Activity.RESULT_CANCELED) {
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        getData();
    }


    private void confirmOutLoginDialog(){
        final AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.confirm_outlogin_dialog, null, false);
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
}
