package com.a360filemanager.goodsq.my_matchbox_3.httpParams;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

/**
 * Created by Administrator on 2016/8/15.
 */
@HttpRequest(host = ServerInterfaceBean.BASE_URL, path = ServerInterfaceBean.PublishComment)
public class PublishPostCommentParams extends RequestParams {

    public PublishPostCommentParams(String content, int postId) {
        this.addBodyParameter("discuss.content", content);
        this.addBodyParameter("discuss.friendCircle.id", postId + "");
        this.addBodyParameter("discuss.user.id", MyApp.getInstance().getUser().getUserId() + "");
    }

}
