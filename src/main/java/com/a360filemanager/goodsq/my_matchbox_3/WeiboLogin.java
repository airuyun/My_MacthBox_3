package com.a360filemanager.goodsq.my_matchbox_3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.a360filemanager.goodsq.my_matchbox_3.bean.WeiboUserInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.ServerUtils;
import com.alibaba.fastjson.JSON;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

import sina.weibo.sdk.openapi.UsersAPI;

/**
 * Created by goodsq on 2016/8/9.
 */
    /*------------------------------------新浪微博第三方授权登录--------------------------------------*/
public class WeiboLogin {

    Context mContext;
    Activity mActivity;
    AuthInfo mAuthInfo;
    SsoHandler mSsoHandler;
    Oauth2AccessToken mAccessToken;
    UsersAPI mUsersAPI;
    //微博的权限
    private static final String WEIBO_APPKEY = "4059594954";
    private static final String SCOPE = "email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write";

    public WeiboLogin(Context mContext, Activity mActivity) {
        this.mContext = mContext;
        this.mActivity = mActivity;

    }

    //1.开始登陆新浪微博第三方授权登录
    public void login() {
        mAuthInfo = new AuthInfo(mContext, WEIBO_APPKEY, "http://www.baidu.com", SCOPE);
        mSsoHandler = new SsoHandler(mActivity, mAuthInfo);
        //发起授权登陆
        mSsoHandler.authorize(authListener);
    }
    //2.显示授权页面
    WeiboAuthListener authListener = new WeiboAuthListener() {
        @Override
        public void onComplete(Bundle bundle) {
            String access_token = bundle.getString("access_token");
            String expires_in = bundle.getString("expires_in");
            //出现问题的地方，要得到Long uid = bundle.getLong("uid");要先转String再转为Long类型，why？
            Long uid = Long.parseLong(bundle.getString("uid"));
            mAccessToken = new Oauth2AccessToken(access_token,expires_in);
            mUsersAPI = new UsersAPI(mContext,WEIBO_APPKEY,mAccessToken);
            mUsersAPI.show(uid,requestListener);

        }

        @Override
        public void onWeiboException(WeiboException e) {
            Log.e("TAG", "----------------" + e.toString());
        }

        @Override
        public void onCancel() {
            Log.e("TAG", "----------------");
        }
    };
    //3.获取用户数据
    WeiboUserInfoBean weiboUserInfoBean;
    //获取用户信息回调，返回的是一个JSon数据
    RequestListener requestListener = new RequestListener() {
        @Override
        public void onComplete(String s) {
            Log.e("TAG","------JSon------"+s);
            //使用fastJSon将JSon数据反序列化
            weiboUserInfoBean = JSON.parseObject(s.toString(),WeiboUserInfoBean.class);
            ServerUtils mServerUtils = new ServerUtils(weiboUserInfoBean.getId()+"",weiboUserInfoBean,"Weibo");
            mServerUtils.loginServer();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Log.e("TAG","------aaaa------");
        }
    };

    public SsoHandler getmSsoHandler() {
        return mSsoHandler;
    }
}
