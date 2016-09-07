package com.a360filemanager.goodsq.my_matchbox_3.httpParams;

import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

/**
 * Created by goodsq on 2016/8/10.
 */
//网址域名：ServerInterfaceBean.BASE_URL；访问接口为：ServerInterfaceBean.Register
@HttpRequest(host = ServerInterfaceBean.BASE_URL,path = ServerInterfaceBean.Register)
public class RegisterServerHttpParams extends RequestParams {//访问服务器时，域名，接口，字段都必须正确，否则无法访问，由服务器编写者提供

    public RegisterServerHttpParams(String account,String password){
        this.addBodyParameter("user.name",account);//访问字段为："user.name",登录名
        this.addBodyParameter("user.passWord",password);
    }
}
