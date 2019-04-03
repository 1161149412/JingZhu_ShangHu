package com.xiaomai.shanghu.tixian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;
import com.xiaomai.shanghu.R;
import com.xiaomai.shanghu.base.BaseActivity;

public class AddBankCardActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_add_bank_card;
    }

    @Override
    public void initView() {
        ImmersionBar.with(this).statusBarColor(R.color.white).fitsSystemWindows(true).statusBarDarkFont(true).init();
    }
}
