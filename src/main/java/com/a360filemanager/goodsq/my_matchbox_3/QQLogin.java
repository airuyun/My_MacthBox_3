package com.a360filemanager.goodsq.my_matchbox_3;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.a360filemanager.goodsq.my_matchbox_3.bean.QQUserBaseDataBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.QQUserInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.ServerUtils;
import com.alibaba.fastjson.JSON;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * Created by goodsq on 2016/8/9.
 */
    /*----------------------------------腾讯QQ第三方授权登录------------------------------------------*/
public class QQLogin {
    Tencent mTencent;
    String TENCENT_APPID = "1105519061";
    UserInfo mUserInfo;
    QQUserBaseDataBean mQQUserBasicInfoBean;
    Context mContext;
    Activity mActivity;

    public QQLogin(Context mContext, Activity mActivity) {
        this.mContext = mContext;
        this.mActivity = mActivity;
    }


    //1.开始腾讯QQ第三方授权登录，并从当前Activity跳到QQ授权登录Activity
    public void login() {
        mTencent = Tencent.createInstance(TENCENT_APPID, mContext);//获取Tencent对象
        mTencent.login(mActivity, "all", qqLoginListener);//开始登录，在这一步会跳转到QQ授权登录页面（授权登录Activity）
    }

    /**
     * 获取用户基本信息
     */
    //2.第一次回调；第三方登录成功，获取第三方用户登录基本信息；跳转到腾讯QQ授权登录页面获取数据的回调监听，使用此监听获取数据
    IUiListener qqLoginListener = new IUiListener() {
        @Override
        public void onComplete(Object o) {
            Log.e("TAg", "-----获取JSon数据1------" + o);
            //返回的Object o是一个JSon数据，包含了一些登录信息，但不包含用户详细信息；使用GSon把JSon数据生成一个QQBean类
            //使用fastJson反序列化，使mQQUserBasicInfoBean类拿到数据
            mQQUserBasicInfoBean = JSON.parseObject(o.toString(), QQUserBaseDataBean.class);
            //拿到QQToken，token问标记，象征的意思
            //腾讯建议开发者在每次应用启动QQ授权登录时调用一次该API(先调用setOpenId、setAccessToken)，以确保每次打开应用时用户都是有登录态的。
            mTencent.setOpenId(mQQUserBasicInfoBean.getOpenid());//设置打开QQ的ID号
            mTencent.setAccessToken(mQQUserBasicInfoBean.getAccess_token(), mQQUserBasicInfoBean.getExpires_in() + "");//设置进入访问标记
            mUserInfo = new UserInfo(mContext, mTencent.getQQToken());//用户详细信息类
            //这个接口获取用户详细信息,用户详细信息通过回调返回
            mUserInfo.getUserInfo(getQQUserInfoListener);
        }

        @Override
        public void onError(UiError uiError) {
        }

        @Override
        public void onCancel() {
        }
    };
    /**
     * 获取用户信息
     */
    QQUserInfoBean mQQUserInfoBean;
    //3.第二次回调;第一次回调成功获取用户基本信息后，通过第二次回调获取用户详细信息，包括昵称、性别、年龄、省份等信息
    IUiListener getQQUserInfoListener = new IUiListener() {
        @Override
        public void onComplete(Object o) {
            //返回的Object o是一个JSon数据，包含了QQ用户详细信息；使用GSon把JSon数据生成一个QQUserInfoBean类
            Log.e("TAG", "------获取JSon数据2--------" + o.toString());
            //fastJson反序列化，使mQQUserInfoBean拿到数据
            mQQUserInfoBean = JSON.parseObject(o.toString(), QQUserInfoBean.class);
            //登录服务器,登录自己的服务器，这一步是访问服务器
            ServerUtils mServerUtils = new ServerUtils(mContext, mTencent.getOpenId(), mQQUserInfoBean, "QQ");
            mServerUtils.loginServer();
            //服务器注册参数
            // 1.openId 用户名
            // 2.头像，
            // 3.昵称
            // 4.sex
        }

        @Override
        public void onError(UiError uiError) {
        }

        @Override
        public void onCancel() {
        }
    };

    public Tencent getmTencent() {
        return this.mTencent;
    }

    public IUiListener getQqLoginListener() {
        return this.qqLoginListener;
    }
}
