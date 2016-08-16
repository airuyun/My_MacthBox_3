package com.a360filemanager.goodsq.my_matchbox_3.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

public class BoxUtils {

    public static final void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    // 进度条对话框
    public static final ProgressDialog getProgressDialog(Context context, String title, String msg) {
        ProgressDialog pd = new ProgressDialog(context);
        pd.setTitle(title);
        pd.setMessage(msg);
        pd.setCancelable(false);
        return pd;
    }

    // 进度条对话框
    public static final ProgressDialog getProgressDialog(Context context, String title, String msg, int max) {
        ProgressDialog pd = new ProgressDialog(context);
        pd.setTitle(title);
        pd.setMessage(msg);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMax(max);
        pd.setCancelable(false);
        return pd;
    }

}
