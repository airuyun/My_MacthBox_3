package com.a360filemanager.goodsq.my_matchbox_3;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import com.a360filemanager.goodsq.my_matchbox_3.bean.UserInfoBean;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.smssdk.SMSSDK;

/**
 * Created by goodsq on 2016/8/9.
 */
public class MyApp extends Application {

    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        x.Ext.init(this);//初始化XUtils
        /*---------------------------------聚合数据短信验证初始化---------------------------------*/
        SMSSDK.initSDK(this, "16b035067a57e", "fc7261923128e0e38542a6549a52a4e1");//第二个参数为：App Key；第三个参数为：App Secret
        /*---------------------------------------环信---------------------------------------------*/
        //在 MyApp 中初始化，由于开启了其他进程的服务， app 会初始化两次，所以加上检测方法
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        if (processAppName == null || !processAppName.equalsIgnoreCase(this.getPackageName())) {
            //  则此 application::onCreate  是被 service  调用的，直接返回
            return;
        }
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的;如果false则需要验证，true 则不需要
        options.setAcceptInvitationAlways(true);
        //初始化
        EaseUI.getInstance().init(this, options);
        //EMClient.getInstance().init(this, options);//不能用这一句初始化
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
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
    private UserInfoBean user = UserInfoBean.getInstance();

    public UserInfoBean getUser() {
        return user;
    }

    public void setUser(UserInfoBean user) {
        this.user = user;
    }

    /*========================================================*/
    private List<File> allPic;

    public List<File> getAllPic() {
        return allPic;
    }

    public void setAllPic(List<File> allPic) {
        this.allPic = allPic;
    }

    /*========================================================*/
    private boolean isSendCode;

    public boolean isSendCode() {
        return isSendCode;
    }

    Bitmap portraitBitmap;//头像

    public Bitmap getPortraitBitmap() {
        return portraitBitmap;
    }

    public void setPortraitBatmap(Bitmap portraitBatmap) {
        this.portraitBitmap = portraitBatmap;
    }

    /**
     * 剩余时间
     */
    private boolean noSend;

    public boolean noSendVerificationCode() {
        return noSend;
    }

    public void remainTime(final long time) {
        new Thread() {
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

    public boolean inputPassword = false;
    public boolean inputAccount = false;
    public boolean isThirdPartyLogin = false;



    int friendId;
    String friendNickName;
    String friendHead;

    public String getFriendHead() {
        return friendHead;
    }

    public void setFriendHead(String friendHead) {
        this.friendHead = friendHead;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getFriendNickName() {
        return friendNickName;
    }

    public void setFriendNickName(String friendNickName) {
        this.friendNickName = friendNickName;
    }

    /*========================================================*/
    public String getCachePath() {
        File cacheDir;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir = getExternalCacheDir();
        else
            cacheDir = getCacheDir();
        if (!cacheDir.exists())
            cacheDir.mkdirs();
        return cacheDir.getAbsolutePath();
    }

    /*========================================================*/
    //上述获取进程名的方法如下
    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
// Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
}
