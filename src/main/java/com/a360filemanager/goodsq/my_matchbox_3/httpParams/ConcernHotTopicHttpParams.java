package com.a360filemanager.goodsq.my_matchbox_3.httpParams;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

/**
 * Created by goodsq on 2016/8/16.
 */
@HttpRequest(host = ServerInterfaceBean.BASE_URL,path = ServerInterfaceBean.CONCERN_HOT_TOPIC)
public class ConcernHotTopicHttpParams extends RequestParams{
    public ConcernHotTopicHttpParams (int friendId){
        this.addBodyParameter("userTopic.user.id", MyApp.getInstance().getUser().getUserId() + "");//friend.user.i,登录者的id
        this.addBodyParameter("userTopic.topic.id", friendId + "");//friend.beuser.id,关注话题的id
    }
}
