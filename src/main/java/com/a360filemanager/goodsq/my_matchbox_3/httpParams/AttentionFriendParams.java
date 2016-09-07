package com.a360filemanager.goodsq.my_matchbox_3.httpParams;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

/**
 * Created by Administrator on 2016/8/11.
 */

/*======================================关注好友请求参数类========================================*/
@HttpRequest(host = ServerInterfaceBean.BASE_URL, path = ServerInterfaceBean.AttentionFriend)
public class AttentionFriendParams extends RequestParams {

    public AttentionFriendParams(int friendId) {
        this.addBodyParameter("friend.user.id", MyApp.getInstance().getUser().getUserId() + "");
        this.addBodyParameter("friend.beuser.id", friendId + "");
    }

}
