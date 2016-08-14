package com.a360filemanager.goodsq.my_matchbox_3.httpParams;

import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

/**
 * Created by goodsq on 2016/8/10.
 */
//xUtils注解
@HttpRequest(host = ServerInterfaceBean.BASE_URL, path = ServerInterfaceBean.Login)
public class LoginServerHttpParams extends RequestParams {

    public LoginServerHttpParams(String id, String password) {
        this.addBodyParameter("name", id);
        this.addBodyParameter("password", password);
    }


}
