package com.a360filemanager.goodsq.my_matchbox_3.httpUtils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.activity.EditDataActivity;
import com.a360filemanager.goodsq.my_matchbox_3.activity.HomeActivity;
import com.a360filemanager.goodsq.my_matchbox_3.activity.SelectTopicActivity;
import com.a360filemanager.goodsq.my_matchbox_3.bean.QQUserInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.UserLoginInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.UserRegisterInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.WeiboUserInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.utils.BoxUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;

/**
 * Created by goodsq on 2016/8/10.
 */
public class ServerUtils {
    String id;
    String name = "";
    Context mContext;
    String password;
    String loginPassword;
    boolean loginType;
    QQUserInfoBean mQQUserInfoBean;
    WeiboUserInfoBean mWeiboUserInfoBean;
    Dialog dialog;

    //非第三方登录使用，即电话号码注册、登录，id为电话号码
    public ServerUtils(Context mContext, String id, String loginPassword, boolean loginType) {
        this.mContext = mContext;
        this.id = id;
        this.loginPassword = loginPassword;
        this.loginType = loginType;
    }

    //QQ登录使用
    public ServerUtils(Context mContext, String id, QQUserInfoBean mQQUserInfoBean, String name) {
        this.mContext = mContext;
        this.id = id;
        this.name = name;
        this.mQQUserInfoBean = mQQUserInfoBean;
    }

    //新浪微博登录使用
    public ServerUtils(Context mContext,String id, WeiboUserInfoBean mWeiboUserInfoBean, String name) {
        this.mContext = mContext;
        this.id = id;
        this.name = name;
        this.mWeiboUserInfoBean = mWeiboUserInfoBean;
    }

    public void loginServer() {//登录服务器,登录自己的服务器，这一步是访问服务器
        if (loginType) {//非第三方登录
            password = loginPassword;
            dialog = BoxUtils.getProgressDialog(mContext, "", "");
            dialog.show();
        } else {//第三方登录
            password = ServerInterfaceBean.DEFAULT_PASSWORD;
        }
        LoginServerHttpUtils.userLoginService(id, password, loginServerCallback);
    }

    /*==========================================登录==============================================*/
    //1.登录服务器的回调监听，用于获取登录服务器后返回的数据
    CallbackUtils<UserLoginInfoBean> loginServerCallback = new CallbackUtils<UserLoginInfoBean>() {
        @Override
        public void onSuccess(UserLoginInfoBean userInfo) {
            if (userInfo.getResult().equals("0")) {//登录成功
                Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                if (name.equals("QQ") || name.equals("Weibo")) {
                    mContext.startActivity(new Intent(mContext, HomeActivity.class));
                }
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            } else {//登录失败，进行注册
                Toast.makeText(mContext, "登录失败", Toast.LENGTH_SHORT).show();
                LoginServerHttpUtils.userRegister(id, password, registerServerCallback);//注册
            }
        }

        @Override
        public void onFinish() {
        }
    };
    /*=========================================注册===============================================*/
    //2.注册服务器的回调监听，用于获取在服务器注册后返回的数据
    CallbackUtils<UserRegisterInfoBean> registerServerCallback = new CallbackUtils<UserRegisterInfoBean>() {
        @Override
        public void onSuccess(UserRegisterInfoBean result) {
            if (result.getResult().equals("0")) {//注册成功

                Toast.makeText(mContext, "注册成功", Toast.LENGTH_SHORT).show();
                //注册成功，开始修改资料
                UserLoginInfoBean user = MyApp.getInstance().getUser();
                user.setUserId(result.getUserId());
                if (name.equals("QQ")) {
                    user.setUsername(mQQUserInfoBean.getNickname());
                    user.setSex(mQQUserInfoBean.getGender());
                    user.setMyInfo(mQQUserInfoBean.getCity());
                    //user.setUrl(mQQUserInfoBean.getFigureurl_qq_2());
                    LoginServerHttpUtils.update(user, updateServerCallback);

                } else if (name.equals("Weibo")) {
                    user.setUsername(mWeiboUserInfoBean.getScreen_name());
                    user.setSex(mWeiboUserInfoBean.getGender());
                    user.setMyInfo(mWeiboUserInfoBean.getCity());
                    //user.setUrl(mWeiboUserInfoBean.getUrl());
                    LoginServerHttpUtils.update(user, updateServerCallback);

                } else if (name.equals("Wechat")) {
                } else {
                    LoginServerHttpUtils.update(user, updateServerCallback);
                }
            } else {//注册失败
                Toast.makeText(mContext, "注册失败", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFinish() {
            dialog.dismiss();
        }
    };
    /*==========================================修改资料==========================================*/
    //3.更新服务器用户数据的回调监听，用于获取服务器用户数据更新后返回的数据
    CallbackUtils<Boolean> updateServerCallback = new CallbackUtils<Boolean>() {
        @Override
        public void onSuccess(Boolean aBoolean) {
            if (aBoolean) {
                //资料修改成功
                if (name == "QQ" || name == "Weibo") {
                    mContext.startActivity(new Intent(mContext, SelectTopicActivity.class));
                }else {
                    mContext.startActivity(new Intent(mContext, EditDataActivity.class));
                }
            } else {
                //资料修改失败
            }
        }

        @Override
        public void onFinish() {
            dialog.dismiss();
        }
    };
}
