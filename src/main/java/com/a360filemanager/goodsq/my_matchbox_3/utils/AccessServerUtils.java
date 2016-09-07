package com.a360filemanager.goodsq.my_matchbox_3.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.activity.HomeActivity;
import com.a360filemanager.goodsq.my_matchbox_3.bean.QQUserInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.UserInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.UserRegisterInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.WeiboUserInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.AccessServerHttpUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by goodsq on 2016/8/10.
 */
public class AccessServerUtils {
    Dialog dialog;
    String account;
    String type;
    Context mContext;
    String password;
    QQUserInfoBean mQQUserInfoBean;
    WeiboUserInfoBean mWeiboUserInfoBean;

    //非第三方登录使用，即电话号码为账号
    public AccessServerUtils(Context mContext, String account, String password, String type) {
        this.mContext = mContext;
        this.account = account;
        this.password = password;
        this.type = type;
    }

    //QQ登录使用
    public AccessServerUtils(Context mContext, String account, QQUserInfoBean mQQUserInfoBean, String type) {
        this.mContext = mContext;
        this.account = account;
        this.password = ServerInterfaceBean.DEFAULT_PASSWORD;
        this.type = type;
        this.mQQUserInfoBean = mQQUserInfoBean;
    }

    //新浪微博登录使用
    public AccessServerUtils(Context mContext, String account, WeiboUserInfoBean mWeiboUserInfoBean, String type) {
        this.mContext = mContext;
        this.account = account;
        this.password = ServerInterfaceBean.DEFAULT_PASSWORD;
        this.type = type;
        this.mWeiboUserInfoBean = mWeiboUserInfoBean;
    }

    /*=======================================访问服务器===========================================*/
    public void loginServer() {//登录服务器,登录自己的服务器
        dialog = BoxUtils.getProgressDialog(mContext, "", "");
        dialog.show();
        AccessServerHttpUtils.userLoginService(account, password, loginServerCallback);
    }

    public void registerServer(){//在服务器中进行注册
        dialog = BoxUtils.getProgressDialog(mContext, "", "");
        dialog.show();
        AccessServerHttpUtils.userRegister(account, password, registerServerCallback);//注册
    }

    /*==========================================登录==============================================*/
    //1.登录服务器的回调监听，用于获取登录服务器后返回的数据
    CallbackUtils<UserInfoBean> loginServerCallback = new CallbackUtils<UserInfoBean>() {

        @Override
        public void onSuccess(UserInfoBean userInfoBean) {
            if (userInfoBean.getResult().equals("0")) {//登录成功
                dismissDialog();
                if (type.equals("Phone")) {
                    MyApp.getInstance().isThirdPartyLogin = false;
                } else if (type.equals("QQ") || type.equals("Weibo") || type.equals("Weixin")) {
                    MyApp.getInstance().isThirdPartyLogin = true;
                }
                MyApp.getInstance().setUser(userInfoBean);
                Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                mContext.startActivity(new Intent(mContext, HomeActivity.class));

            } else if (userInfoBean.getResult().equals("1")){//登录失败
                if (type.equals("Phone")) {
                    dismissDialog();
                    Toast.makeText(mContext, "账号、密码错误", Toast.LENGTH_SHORT).show();
                    Toast.makeText(mContext, "或此账号肯能还没有注册？", Toast.LENGTH_SHORT).show();
                } else
                    AccessServerHttpUtils.userRegister(account, password, registerServerCallback);//QQ、微信、微博第三方登录失败，进行注册
            }
        }

        @Override
        public void onFinish() {
        }
    };
    /*=========================================注册===============================================*/
    //2.注册服务器的回调监听，用于获取在服务器注册后返回的数据
    UserInfoBean user;
    CallbackUtils<UserRegisterInfoBean> registerServerCallback = new CallbackUtils<UserRegisterInfoBean>() {
        @Override
        public void onSuccess(UserRegisterInfoBean result) {
            dismissDialog();
            if (result.getResult().equals("0")) {//注册成功
                Toast.makeText(mContext, "注册成功", Toast.LENGTH_SHORT).show();
                user = MyApp.getInstance().getUser();
                user.setUserId(result.getUserId());
                if (type.equals("QQ")) {//注册成功，开始修改资料

                    user.setNickName(mQQUserInfoBean.getNickname());//昵称
                    user.setSex(mQQUserInfoBean.getGender());//性别
                    user.setMyInfo(mQQUserInfoBean.getMsg());//个人资料
                    user.setUrl(mQQUserInfoBean.getFigureurl_qq_2());//头像
                    downLoadImg(user.getUrl(), handler);
                } else if (type.equals("Weibo")) {
                    user.setNickName(mWeiboUserInfoBean.getScreen_name());
                    user.setSex(mWeiboUserInfoBean.getGender());
                    user.setMyInfo(mWeiboUserInfoBean.getDescription());
                    user.setUrl(mWeiboUserInfoBean.getAvatar_hd());
                    downLoadImg(user.getUrl(), handler);
                } else if (type.equals("Wechat")) {
                } else if (type.equals("Phone")) {
                    user.setNickName(MyApp.getInstance().getUser().getNickName());
                    user.setUrl(MyApp.getInstance().getUser().getUrl());
                }
                    AccessServerHttpUtils.update(user, updateServerCallback);

            }else if (result.getResult().equals("3"))
                Toast.makeText(mContext, "用户已存在", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(mContext, "注册失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFinish() {}
    };
    /*==========================================修改资料==========================================*/
    //3.更新服务器用户数据的回调监听，用于获取服务器用户数据更新后返回的数据
    CallbackUtils<Boolean> updateServerCallback = new CallbackUtils<Boolean>() {
        @Override
        public void onSuccess(Boolean aBoolean) {
            if (aBoolean) {
                Toast.makeText(mContext, "修改资料成功", Toast.LENGTH_SHORT).show();
                //资料修改成功
                if (type.equals("QQ") || type.equals("Weibo") || type.equals("Weixin"))
                    MyApp.getInstance().isThirdPartyLogin = true;
                else
                    MyApp.getInstance().isThirdPartyLogin = false;
                    mContext.startActivity(new Intent(mContext, HomeActivity.class));
            } else {
                //资料修改失败
                Toast.makeText(mContext, "修改资料失败", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFinish() {}
    };

    private void downLoadImg(final String url, final Handler handler) {
        new Thread(){
            @Override
            public void run() {
                super.run();
        InputStream is = null;
        try {
            is = new URL(url).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MatchBox/" + System.currentTimeMillis() + ".jpg";
        File file = new File(path);
        file.getParentFile().mkdir();
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Message message = handler.obtainMessage();
        message.obj = path;
        handler.sendMessage(message);
            }
        }.start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            user.setLocalImage(msg.obj.toString());
            MyApp.getInstance().setUser(user);
            SharedPreferences sp = mContext.getSharedPreferences("autoLogin", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putLong("userId", user.getUserId());
            editor.commit();
        }
    };

    public void dismissDialog(){
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
