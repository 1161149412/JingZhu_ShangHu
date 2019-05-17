package com.xiaomai.shanghu.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.xiaomai.shanghu.R;

/**
 * Created by EDZ on 2019/4/25.
 */

public class DialogUtils {

    public static Dialog showDialog_progressbar(Context context) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        View view = LayoutInflater.from(context).inflate(R.layout.dialong_progressbar, null, false);
        dialog.setView(view);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

        return dialog;
    }

    public static void closeDialog(Dialog mDialogUtils) {
        if (mDialogUtils != null && mDialogUtils.isShowing()) {
            mDialogUtils.dismiss();
        }
    }


}
