package com.a360filemanager.goodsq.my_matchbox_3.httpParams;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

/**
 * Created by Administrator on 2016/8/16.
 */
@HttpRequest(host = ServerInterfaceBean.BASE_URL, path = ServerInterfaceBean.FriendPost)
public class GetFriendPostParams extends RequestParams {

    public GetFriendPostParams() {
        this.addBodyParameter("userId", MyApp.getInstance().getUser().getUserId() + "");
    }
}
