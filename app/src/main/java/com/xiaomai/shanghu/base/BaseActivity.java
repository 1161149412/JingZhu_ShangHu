package com.xiaomai.shanghu.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.xiaomai.shanghu.LoginActivity;
import com.xiaomai.shanghu.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;
    public    Bundle savedInstanceState;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        this.savedInstanceState=savedInstanceState;
        unbinder = ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarColor(R.color.appbar).fitsSystemWindows(true).statusBarDarkFont(true).init();
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);//关闭页面自动隐藏软键盘
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化视图
     */
    public abstract void initView();

    protected void toClass(Context context, Class<? extends BaseActivity> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }
    protected void toClass_Empty(Context context, Class<? extends BaseActivity> clazz) {
        Intent intent = new Intent(context, clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    protected void signOutDialog(final Context context) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        View view = LayoutInflater.from(this).inflate(R.layout.confirm_dialong, null, false);
        dialog.setView(view);
        dialog.setCancelable(false);
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                usertoken.edit().clear().commit();
                toClass(context,LoginActivity.class);
                finish();
            }
        });
        dialog.show();
    }


}
