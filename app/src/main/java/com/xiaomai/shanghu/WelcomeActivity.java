package com.xiaomai.shanghu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xiaomai.shanghu.base.BaseActivity;
import com.xiaomai.shanghu.bean.GetCodeBean;
import com.xiaomai.shanghu.net.RetrofitClient;
import com.xiaomai.shanghu.utils.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WelcomeActivity extends BaseActivity {

    private Intent intent;
    private Intent intent1;
    private SharedPreferences usertoken;
    private String token;
    private long exitTime = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        ImmersionBar.with(this).statusBarColor(R.color.white).fitsSystemWindows(true).statusBarDarkFont(true).init();

        usertoken= getSharedPreferences("mytoken", 0);
        token = usertoken.getString("token","0");

        Handler handler = new Handler();
        intent= new Intent(this,LoginActivity.class);
        intent1 = new Intent(this,MainActivity.class);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //需要执行的代码
                if(token.equals("0")){
                    startActivity(intent);
                    finish();
                }else {
                    startActivity(intent1);
                    finish();
                }
            }
        },2000);
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


    //获取验证码
    private void getData(String mobile) {
        RetrofitClient.getInstance().getApi_login().getCaptchaTest(mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<GetCodeBean>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<GetCodeBean>() {
                    @Override
                    public void accept(GetCodeBean bean) throws Exception {
                        if (bean.getCode()==1){
                            ToastUtil.showShortToast("发送成功");
                        }else if(bean.getCode()==-1){
                            ToastUtil.showShortToast("您还不是商户");
                        }
                        Log.d("tag", "请求成功");

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("tag", "请求错误");
                    }
                });
    }

}
