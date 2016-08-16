package com.a360filemanager.goodsq.my_matchbox_3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by goodsq on 2016/8/8.
 */
public class WelcomeDialog extends Dialog {

    public WelcomeDialog(Context context) {
        super(context, R.style.WelcomeDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_welcome);
        setCancelable(false);//设置为setCancelable(true)时，点击ProgressDialog以外的区域可以让ProgressDialog dismiss掉

    }

    public void show() {
        super.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                //在此加载数据,如果加载数据小于3s则等待，大于3s强行跳转，欢迎页面只显示3秒时间
                long endTime = System.currentTimeMillis();
                if (endTime - startTime < 3000) {
                    try {
                        Thread.sleep(3000 - (endTime - startTime));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                WelcomeDialog.this.dismiss();
            }
        }
    };
}
