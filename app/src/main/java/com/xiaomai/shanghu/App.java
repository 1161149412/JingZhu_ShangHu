package com.xiaomai.shanghu;

import android.app.Application;
import android.content.Context;

import cn.leo.click.SingleClickManager;


/**
 * Created by Administrator on 2018/11/14.
 */

public class App extends Application {
    public static Context content;
    @Override
    public void onCreate() {
        super.onCreate();
        content=this.getApplicationContext();

        //出始化防止重复点击
        SingleClickManager.setClickInterval(800);
    }

}
