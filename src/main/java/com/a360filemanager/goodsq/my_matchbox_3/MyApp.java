package com.a360filemanager.goodsq.my_matchbox_3;

import android.app.Activity;
import android.app.Application;

import com.a360filemanager.goodsq.my_matchbox_3.bean.UserLoginInfoBean;

import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
     * 循环遍历退出
     **/
    List<Activity> activities = new ArrayList<>();

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void exit() {
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i) != null) {
                activities.get(i).finish();
            }
        }
    }

    public boolean isLogin() {
        if (user == null || user.getUserId() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 共享数据单元
     */
    private UserLoginInfoBean user;

    public UserLoginInfoBean getUser() {
        return user;
    }

    public void setUser(UserLoginInfoBean user) {
        this.user = user;
    }

    private List<File> allPic;

    public List<File> getAllPic() {
        return allPic;
    }

    public void setAllPic(List<File> allPic) {
        this.allPic = allPic;
    }

    private boolean isSendCode;

    public boolean isSendCode() {
        return isSendCode;
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
    public boolean a = false;
    public boolean b = false;
}
