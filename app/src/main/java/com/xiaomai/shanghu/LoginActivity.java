package com.xiaomai.shanghu;

import android.os.Bundle;
import android.widget.TextView;

import com.xiaomai.shanghu.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        toClass(this, MainActivity.class);
    }
}
