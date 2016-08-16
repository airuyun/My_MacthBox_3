package com.a360filemanager.goodsq.my_matchbox_3.httpUtils;

import android.util.Log;

import com.a360filemanager.goodsq.my_matchbox_3.bean.UserLoginInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.UserRegisterInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.LoginServerHttpParams;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.RegisterServerHttpParams;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.UpdateServerHttpParams;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.ResultCallback;
import com.alibaba.fastjson.JSON;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;

/**
 * Created by goodsq on 2016/8/10.
 */
public class LoginServerHttpUtils {

    //登录服务器
    public static final void userLoginService(String id, String password, final CallbackUtils<UserLoginInfoBean> callback) {
        LoginServerHttpParams loginParams = new LoginServerHttpParams(id, password);
        x.http().post(loginParams, new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "-------result--------" + result);//获取JSon数据,登录成功和登录失败返回的JSon参数不同
                //使用fastJSon反序列化，获得服务器返回的数据，从服务器获取的JSon数据应该是由编写服务器的人提供，不然很难知道反序列化后具体有什么数据
                UserLoginInfoBean userInfoBean = JSON.parseObject(result, UserLoginInfoBean.class);
                callback.onSuccess(userInfoBean);
            }

            @Override
            public void onFinished() {
                callback.onFinish();
            }
        });
    }

    //在服务器中进行注册
    public static final void userRegister(String id, String password, final CallbackUtils<UserRegisterInfoBean> callback) {//用final修饰，表示继承此类的子类不能修改此方法，即对此方法进行锁定
        RegisterServerHttpParams registerParams = new RegisterServerHttpParams(id, password);
        x.http().post(registerParams, new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "----abcd-----" + result.toString());//获取JSon数据
                UserRegisterInfoBean userRegisterInfoBean = JSON.parseObject(result, UserRegisterInfoBean.class);
                callback.onSuccess(userRegisterInfoBean);
            }

            @Override
            public void onFinished() {
                callback.onFinish();
            }
        });
    }

    //在服务器中修改资料
    public static final void update(UserLoginInfoBean user, final CallbackUtils<Boolean> callback) {
        UpdateServerHttpParams updateParams = new UpdateServerHttpParams(user);
        Log.e("TAG","------------------正在修改资料");
        x.http().post(updateParams, new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","------------------修改资料成功");
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
}
