package com.xiaomai.shanghu;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xiaomai.shanghu.base.BaseActivity;
import com.xiaomai.shanghu.bean.GetCodeBean;
import com.xiaomai.shanghu.bean.UserLoginBean;
import com.xiaomai.shanghu.net.RetrofitClient;
import com.xiaomai.shanghu.utils.CountDownTimerUtils;
import com.xiaomai.shanghu.utils.DialogUtils;
import com.xiaomai.shanghu.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.et_index_tel)
    EditText etIndexTel;
    @BindView(R.id.et_index_code)
    EditText etIndexCode;
    @BindView(R.id.bt_index_getcode)
    TextView btIndexGetcode;

    private String strTel, strCode;
    private long exitTime = 0;

    private SharedPreferences usertoken,userTel;
    private SharedPreferences.Editor editor,editorTel;
    private Dialog dialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        usertoken = getSharedPreferences("mytoken", 0);
        editor = usertoken.edit();

        userTel = getSharedPreferences("userTel", 0);
        editorTel = userTel.edit();

        etIndexTel.setText(userTel.getString("Tel",""));
    }

    //获取验证码
    private void getData(String mobile) {
        dialog = DialogUtils.showDialog_progressbar(this);
                RetrofitClient.getInstance().getApi_login().getCaptchaTest(mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetCodeBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetCodeBean>() {
                    @Override
                    public void accept(GetCodeBean bean) throws Exception {
                        if (bean.getCode()==1){
                            ToastUtil.showShortToast("发送成功");
                            CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(btIndexGetcode, 60000, 1000);
                            mCountDownTimerUtils.start();
                        }else if(bean.getCode()==-1){
                            ToastUtil.showShortToast("您还不是商户");
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

    //登录
    private void getUserInfo() {
        Map<String,String> params = new HashMap<String,String>();
        params.put("captcha",strCode);
        params.put("mobile",strTel);

        Gson gson = new Gson();
        String strJson = gson.toJson(params);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), strJson);
        RetrofitClient.getInstance().getApi_login().getUserInfo(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<UserLoginBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<UserLoginBean>() {
                    @Override
                    public void accept(UserLoginBean bean) throws Exception {
                        if (bean.getCode()==1){
                            editor.putString("token",bean.getData().getWeixinToken());
                            editor.commit();
                            editorTel.putString("Tel",etIndexTel.getText().toString().trim());
                            editorTel.commit();

                            toClass(LoginActivity.this, MainActivity.class);
                            ToastUtil.showShortToast("登录成功");
                            finish();
                        }else {
                            ToastUtil.showShortToast(""+bean.getMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("tag", "登录：请求错误");
                    }
                });
    }

    @OnClick({R.id.bt_index_getcode, R.id.bt_login})
    public void onViewClicked(View view) {
        strCode = etIndexCode.getText().toString().trim();
        strTel = etIndexTel.getText().toString().trim();
        switch (view.getId()) {
            case R.id.bt_index_getcode:
                if (TextUtils.isEmpty(strTel)) {
                    ToastUtil.showShortToast("电话号码不能为空");
                } else {
                    getData(strTel);
                }
                break;
            case R.id.bt_login:
                if (TextUtils.isEmpty(strTel) || TextUtils.isEmpty(strCode)) {
                    ToastUtil.showShortToast("电话号码或者验证码不能为空");
                } else {
                    getUserInfo();
                }

                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @OnTextChanged({R.id.et_index_tel, R.id.et_index_code})
    public void onTextChanged(CharSequence text) {
        strTel = etIndexTel.getText().toString().trim();
        strCode = etIndexCode.getText().toString().trim();
        if (TextUtils.isEmpty(strTel) || TextUtils.isEmpty(strCode)) {
            btLogin.setBackgroundResource(R.drawable.login_button);
            btLogin.setEnabled(false);
        } else {
            btLogin.setBackgroundResource(R.drawable.login_button_true);
            btLogin.setEnabled(true);
        }
    }



}
