package com.a360filemanager.goodsq.my_matchbox_3.httpUtils;

import android.util.Log;
import android.widget.Toast;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.bean.FindUserBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.UserInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.UserRegisterInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.GetUserInfoParams;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.LoginServerHttpParams;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.RegisterServerHttpParams;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.UpdateServerHttpParams;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.ResultCallback;
import com.alibaba.fastjson.JSON;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;

/**
 * Created by goodsq on 2016/8/10.
 */
public class AccessServerHttpUtils {

    //登录服务器
    public static final void userLoginService(String account, String password, final CallbackUtils<UserInfoBean> callback) {
        LoginServerHttpParams loginParams = new LoginServerHttpParams(account, password);
        x.http().post(loginParams, new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "-------result111222333--------" + result);//获取JSon数据,登录成功和登录失败返回的JSon参数不同，从result中获取服务器自动分配给每个账号的userId
                //使用fastJSon反序列化，获得服务器返回的数据，从服务器获取的JSon数据应该是由编写服务器的人提供，不然很难知道反序列化后具体有什么数据
                UserInfoBean userInfoBean = JSON.parseObject(result, UserInfoBean.class);
                Log.e("TAG", "-------resultaaaaaa--------" + userInfoBean.getUsername()+"   "+userInfoBean.getUserId());
                callback.onSuccess(userInfoBean);
                //环信登录
                EMClient.getInstance().login(MyApp.getInstance().getUser().getUserId() + "", ServerInterfaceBean.DEFAULT_PASSWORD, new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        Log.e("main", "登录聊天服务器成功！");
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                        Log.e("main", "登录聊天服务器失败！");
                    }
                });
            }

            @Override
            public void onFinished() {
                callback.onFinish();
            }
        });
    }

    //在服务器中进行注册
    public static final void userRegister(String account, String password, final CallbackUtils<UserRegisterInfoBean> callback) {//用final修饰，表示继承此类的子类不能修改此方法，即对此方法进行锁定
        RegisterServerHttpParams registerParams = new RegisterServerHttpParams(account, password);
        x.http().post(registerParams, new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "----abcd-----" + result.toString());//获取JSon数据
                UserRegisterInfoBean userRegisterInfoBean = JSON.parseObject(result, UserRegisterInfoBean.class);
                callback.onSuccess(userRegisterInfoBean);
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        //环信注册
                        //注册失败会抛出HyphenateException
                        try {
                            EMClient.getInstance().createAccount(MyApp.getInstance().getUser().getUserId() + "", ServerInterfaceBean.DEFAULT_PASSWORD);//同步方法
                            Log.e("main", "--------------------注册聊天服务器成功！"+MyApp.getInstance().getUser().getUserId());
                        } catch (HyphenateException e) {
                            Log.e("main", "--------------------注册聊天服务器失败！");
                            e.printStackTrace();
                        }
                    }
                }.start();
            }

            @Override
            public void onFinished() {
                callback.onFinish();
            }
        });
    }

    //在服务器中修改资料
    public static final void update(UserInfoBean user, final CallbackUtils<Boolean> callback) {
        UpdateServerHttpParams updateParams = new UpdateServerHttpParams(user);
        Log.e("TAG", "------------------正在修改资料");
        x.http().post(updateParams, new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "------------------修改资料成功");

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("TAG", "------------------"+(result == null?"是null":"不是null"));
                String res = jsonObject.optString("result");
                if (res.equals("0")) {
                    callback.onSuccess(true);
                } else {
                    callback.onSuccess(false);
                }
            }

            @Override
            public void onFinished() {
                callback.onFinish();
            }
        });
    }

    /**
     * 获取用户资料
     */
    public static final void getUserInfo(int userId, final CallbackUtils<UserInfoBean> callback) {
        GetUserInfoParams params = new GetUserInfoParams(userId);
        x.http().post(params, new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                FindUserBean bean = JSON.parseObject(result, FindUserBean.class);
                if (bean.getResult().equals("0")) {
                    UserInfoBean user = UserInfoBean.getInstance();
                    user.setNickName(bean.getUserName());
                    user.setUsername(bean.getName());
                    user.setUserId(bean.getUserId());
                    user.setSex(bean.getSex());
                    user.setMyActionCount(bean.getMyActionCount());
                    user.setFansCount(bean.getFansCount());
                    user.setUrl(bean.getUrl());
                    user.setMyInfo(bean.getMyInfo());
                    callback.onSuccess(user);
                }
            }

            @Override
            public void onFinished() {
                callback.onFinish();
            }
        });
    }
}
