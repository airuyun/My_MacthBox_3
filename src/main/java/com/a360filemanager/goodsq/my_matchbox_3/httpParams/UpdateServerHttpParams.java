package com.a360filemanager.goodsq.my_matchbox_3.httpParams;

import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.UserLoginInfoBean;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

/**
 * Created by goodsq on 2016/8/10.
 */
//xUtils注解
@HttpRequest(host = ServerInterfaceBean.BASE_URL, path = ServerInterfaceBean.Update)
public class UpdateServerHttpParams extends RequestParams {
    public UpdateServerHttpParams(UserLoginInfoBean user) {
        if (user.getUserId() != 0) {
            this.addBodyParameter("user.id", user.getUserId() + "");
        }
        if (user.getUsername() != null) {
            this.addBodyParameter("user.userName", user.getUsername());
        }
        if (user.getSex() != null) {
            this.addBodyParameter("user.sex", user.getSex());
        }
//        if (user.getUrl() != null) {//头像
//            this.addBodyParameter("doc", user.getUrl());//获取图片出现问题，要先下载图片在更新服务器，否则更新将会失败
//        }
/*        if (user.getMyInfo() != null) {
            this.addBodyParameter("user.myInfo", user.getMyInfo());
        }*/
    }
}
