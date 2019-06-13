package com.xiaomai.yyshanghu.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by EDZ on 2019/4/30.
 * 不自动弹出软键盘
 */

public class HideSoftKeyBoard {
    /**
     * 隐藏软键盘(有输入框)
     * @param context
     * @param mEditText
     */
    public static void hideSoftKeyboard(@NonNull Context context,
                                        @NonNull EditText mEditText)
    {
        InputMethodManager inputmanger = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputmanger.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }


    /**
     * 隐藏软键盘(无输入框或者说无法获取输入框。比如，微信支付时处于未登录状态，此时输入框
     * 是微信的，返回再隐藏键盘)
     * @param context
     */
    public static void hideSoftKeyboard(@NonNull Activity context)
    {
        View view = context.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
