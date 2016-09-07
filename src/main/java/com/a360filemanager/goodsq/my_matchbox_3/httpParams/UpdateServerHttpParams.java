package com.a360filemanager.goodsq.my_matchbox_3.httpParams;

import android.util.Log;

import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.UserInfoBean;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

import java.io.File;

/**
 * Created by goodsq on 2016/8/10.
 */
//xUtils注解
@HttpRequest(host = ServerInterfaceBean.BASE_URL, path = ServerInterfaceBean.Update)
public class UpdateServerHttpParams extends RequestParams {
    public UpdateServerHttpParams(UserInfoBean user) {
        if (user.getUserId() != 0) {//登录名（即账号：电话号码，openId）
            this.addBodyParameter("user.id", user.getUserId() + "");
        }
        if (user.getNickName() != null) {//用户名（即昵称）
            Log.e("TAG","---------------昵称"+user.getNickName());
            this.addBodyParameter("user.userName", user.getNickName());
        }
        if (user.getSex() != null) {//性别
            this.addBodyParameter("user.sex", user.getSex());
        }
        if (user.getMyInfo() != null) {//个人资料
            this.addBodyParameter("user.myInfo", user.getMyInfo());
        }
        if (user.getUrl() != null) {//头像
            Log.e("TAG","---------------有头像"+user.getUrl().toString());
            this.addBodyParameter("doc", new File(user.getLocalImage()));//获取图片出现问题，要先下载图片在更新服务器，否则更新将会失败
        }

    }
}
