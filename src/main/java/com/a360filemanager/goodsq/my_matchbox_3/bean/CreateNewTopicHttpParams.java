package com.a360filemanager.goodsq.my_matchbox_3.bean;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

/**
 * Created by goodsq on 2016/8/23.
 */
@HttpRequest(host = ServerInterfaceBean.BASE_URL,path = ServerInterfaceBean.CreateTopic)
public class CreateNewTopicHttpParams extends RequestParams {

    public CreateNewTopicHttpParams(String topicName    ){
        this.addBodyParameter("userId", MyApp.getInstance().getUser().getUserId()+"");
        addBodyParameter("name", topicName);
    }
}
