package com.xiaomai.shanghu.vipconfig;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.base.BaseActivity;
import com.xiaomai.shanghu.bean.GetCodeBean;
import com.xiaomai.shanghu.net.RetrofitClient;
import com.xiaomai.shanghu.utils.CountDownTimerReductionUtils;
import com.xiaomai.shanghu.utils.DialogUtils;
import com.xiaomai.shanghu.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddVipActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.edit_put_name)
    EditText edit_put_name;
    @BindView(R.id.edit_put_vip_phone)
    EditText edit_put_vip_phone;
    @BindView(R.id.edit_put_phone_code)
    EditText edit_put_phone_code;
    @BindView(R.id.tv_get_code)
    TextView tv_get_code;
    @BindView(R.id.bt_save)
    TextView bt_save;

    private String strTel,name,code;
    private Dialog dialog;
    private SharedPreferences usertoken;
    private String token;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_vip;
    }

    @OnClick({R.id.back,R.id.tv_get_code,R.id.bt_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_get_code://获取验证码
                strTel = edit_put_vip_phone.getText().toString();
                Log.d("tag", ""+strTel);
                if (TextUtils.isEmpty(strTel)) {
                    ToastUtil.showShortToast("电话号码不能为空");
                } else {
                    getData(strTel);
                }
                break;
            case R.id.bt_save://保存
                strTel = edit_put_vip_phone.getText().toString();
                name = edit_put_name.getText().toString();
                code  = edit_put_phone_code.getText().toString();
                if (TextUtils.isEmpty(strTel) || TextUtils.isEmpty(name) || TextUtils.isEmpty(code)) {
                    ToastUtil.showShortToast("任何一项都不能为空");
                } else {
                    addVip(strTel,code,name);
                }

                break;
        }
    }

    @Override
    public void initView() {
        ImmersionBar.with(this).statusBarColor(R.color.white).fitsSystemWindows(true).statusBarDarkFont(true).init();

        usertoken= getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");
    }


    //获取验证码
    private void getData(String mobile) {
        dialog = DialogUtils.showDialog_progressbar(this);

        RetrofitClient.getInstance().getApi_login().sendTestCode(mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetCodeBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetCodeBean>() {
                    @Override
                    public void accept(GetCodeBean bean) throws Exception {
                        if (bean.getCode()==1){
                            ToastUtil.showShortToast("发送成功");
                            CountDownTimerReductionUtils countDownTimerReductionUtils = new CountDownTimerReductionUtils(tv_get_code, 60000, 1000);
                            countDownTimerReductionUtils.start();
                        }else if(bean.getCode()==-1){
                            ToastUtil.showShortToast(""+bean.getMsg());
                        }
                        Log.d("tag", "请求成功");
                        DialogUtils.closeDialog(dialog);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.closeDialog(dialog);
                        Log.d("tag", "请求错误");
                    }
                });
    }


    private void addVip(String mobile,String messageCode,String userName){
        dialog = DialogUtils.showDialog_progressbar(this);

        Condition condition = new Condition(mobile,messageCode,userName);
        Gson gson = new Gson();
        String strJson = gson.toJson(condition);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), strJson);
        RetrofitClient.getInstance().getApi(token).addSave(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetCodeBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetCodeBean>() {
                    @Override
                    public void accept(GetCodeBean bean) throws Exception {
                        if (bean.getCode()==1){
                           ToastUtil.showShortToast(""+bean.getMsg());
                           finish();
                        }else if(bean.getCode()==-1){
                            ToastUtil.showShortToast(""+bean.getMsg());
                        }
                        Log.d("tag", "请求成功");
                        DialogUtils.closeDialog(dialog);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.closeDialog(dialog);
                        Log.d("tag", "请求错误");
                    }
                });
    }


    @OnTextChanged({R.id.edit_put_name, R.id.edit_put_vip_phone,R.id.edit_put_phone_code})
    public void onTextChanged(CharSequence text) {
        strTel = edit_put_name.getText().toString().trim();
        name = edit_put_vip_phone.getText().toString().trim();
        code = edit_put_phone_code.getText().toString().trim();
        if (TextUtils.isEmpty(strTel) || TextUtils.isEmpty(name)||TextUtils.isEmpty(code)) {
            bt_save.setBackgroundResource(R.drawable.login_button);
            bt_save.setEnabled(false);
        } else {
            bt_save.setBackgroundResource(R.drawable.login_button_true);
            bt_save.setEnabled(true);
        }
    }


}
