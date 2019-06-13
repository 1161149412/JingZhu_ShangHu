package com.xiaomai.yyshanghu.utils;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;

/**
 * Created by EDZ on 2019/5/5.
 */

public class AddDefaultScreenAreaUtils {
    /**
     * 增加控件的可点击范围，最大范围只能是父布局所包含的的区域
     */
    public static void addDefaultScreenArea(final View view, final int top, final int bottom, final int left, final int right) {
        final View parent = (View) view.getParent();
        parent.post(new Runnable() {
            public void run() {
                Rect bounds = new Rect();
                view.setEnabled(true);
                view.getHitRect(bounds);

                bounds.top -= top;
                bounds.bottom += bottom;
                bounds.left -= left;
                bounds.right += right;

                TouchDelegate touchDelegate = new TouchDelegate(bounds, view);

                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });

    }
}
