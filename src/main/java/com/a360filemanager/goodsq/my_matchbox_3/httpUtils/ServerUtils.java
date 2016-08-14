package com.a360filemanager.goodsq.my_matchbox_3.httpUtils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.a360filemanager.goodsq.my_matchbox_3.activity.EditDataActivity;
import com.a360filemanager.goodsq.my_matchbox_3.bean.QQUserInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.UserLoginInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.UserRegisterInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.WeiboUserInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;

/**
 * Created by goodsq on 2016/8/10.
 */
public class ServerUtils {
    String id;
    String name;
    Context mContext;
    QQUserInfoBean mQQUserInfoBean;
    WeiboUserInfoBean mWeiboUserInfoBean;

    public ServerUtils(Context mContext, String id, QQUserInfoBean mQQUserInfoBean, String name, int i) {
        this.id = id;
        this.name = name;
        this.mQQUserInfoBean = mQQUserInfoBean;
        this.mContext = mContext;
    }

    public ServerUtils(String id, WeiboUserInfoBean mWeiboUserInfoBean, String name) {
        this.id = id;
        this.name = name;
        this.mWeiboUserInfoBean = mWeiboUserInfoBean;
    }

    public void loginServer() {
        //登录服务器,登录自己的服务器，这一步是访问服务器
        LoginServerHttpUtils.userLoginService(id, ServerInterfaceBean.DEFAULT_PASSWORD, loginServerCallback);
    }

    //1.登录服务器的回调监听，用于获取登录服务器后返回的数据
    CallbackUtils<UserLoginInfoBean> loginServerCallback = new CallbackUtils<UserLoginInfoBean>() {
        @Override
        public void onSuccess(UserLoginInfoBean userInfo) {
            if (userInfo.getResult().equals("0")) {//登录成功
                Log.e("TAG","------登录成功-------");
                Toast.makeText(mContext,"登录成功",Toast.LENGTH_SHORT).show();
                mContext.startActivity(new Intent(mContext, EditDataActivity.class));
            } else {//登录失败，进行注册
                Log.e("TAG","------登录失败-------");
                Toast.makeText(mContext,"登录失败",Toast.LENGTH_SHORT).show();
                LoginServerHttpUtils.userRegister(id, ServerInterfaceBean.DEFAULT_PASSWORD, registerServerCallback);
            }
        }

        @Override
        public void onFinish() {

        }
    };
    //2.注册服务器的回调监听，用于获取在服务器注册后返回的数据
    CallbackUtils<UserRegisterInfoBean> registerServerCallback = new CallbackUtils<UserRegisterInfoBean>() {
        @Override
        public void onSuccess(UserRegisterInfoBean result) {
            if (result.getResult().equals("0")) {//注册成功
                mContext.startActivity(new Intent(mContext, EditDataActivity.class));
                Toast.makeText(mContext,"注册成功",Toast.LENGTH_SHORT).show();
                //注册成功，开始修改资料
                final UserLoginInfoBean user = new UserLoginInfoBean();
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
                    Log.e("TAG", "----------------<><>修改资料-微信");
                } else {
                    Log.e("TAG", "----------------<><>修改资料失败");
                }
            } else {//注册失败
                Toast.makeText(mContext,"注册失败",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFinish() {

        }
    };
    //3.更新服务器用户数据的回调监听，用于获取服务器用户数据更新后返回的数据
    CallbackUtils<Boolean> updateServerCallback = new CallbackUtils<Boolean>() {
        @Override
        public void onSuccess(Boolean aBoolean) {
            if (aBoolean) {
                //注册成功
                Log.e("TAG", "-----registerSuccess?------" + aBoolean);
            } else {
                Log.e("TAG", "-----registerSuccess?------" + aBoolean);
            }
        }

        @Override
        public void onFinish() {

        }
    };
}
