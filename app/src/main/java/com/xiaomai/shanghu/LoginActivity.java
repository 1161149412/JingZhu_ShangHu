package com.xiaomai.shanghu;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.xiaomai.shanghu.base.BaseActivity;
import com.xiaomai.shanghu.bean.GetCodeBean;
import com.xiaomai.shanghu.bean.LoginBean;
import com.xiaomai.shanghu.net.RetrofitClient;

import org.reactivestreams.Subscription;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.bt_login)
    TextView btLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }


    @OnClick(R.id.bt_login)
    public void onViewClicked() {
//        getData();
        toClass(this, MainActivity.class);
    }

    private void getData() {
        Subscription subscription = (Subscription) RetrofitClient.getInstance().getApi().getCode("15983302246")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetCodeBean>() {
                    @Override
                    public void accept(GetCodeBean bean) throws Exception {
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
