package com.a360filemanager.goodsq.my_matchbox_3;

import android.app.Application;

import org.xutils.x;

import cn.smssdk.SMSSDK;

/**
 * Created by goodsq on 2016/8/9.
 */
public class MyApp extends Application {

    private static MyApp instance;

    public static MyApp getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        x.Ext.init(this);//初始化XUtils
        SMSSDK.initSDK(this,"1516301759e68","3d9a8196bb129695e299b7bac1512eaf");//第二个参数为：App Key；第三个参数为：App Secret
    }

    private boolean noSend;
    public boolean noSendVerificationCode(){
        return noSend;
    }

    /**
    * 剩余时间
    * */

    public void remainTime(final long time){

        new Thread(){
            @Override
            public void run() {
                super.run();
                noSend = true;
                try {
                    sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                noSend = false;
            }
        }.start();

    }
}
