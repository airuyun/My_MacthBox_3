package com.a360filemanager.goodsq.my_matchbox_3.httpParams;

import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

/**
 * Created by Administrator on 2016/8/8.
 */
@HttpRequest(host = ServerInterfaceBean.BASE_URL, path = ServerInterfaceBean.UserInfo)
public class GetUserInfoParams extends RequestParams {

    public GetUserInfoParams(int userId) {
        this.addBodyParameter("userId", "" + userId);
    }


}
