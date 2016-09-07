package com.a360filemanager.goodsq.my_matchbox_3.httpParams;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

/**
 * Created by goodsq on 2016/8/29.
 */
@HttpRequest(host = ServerInterfaceBean.BASE_URL,path = ServerInterfaceBean.GetRecommendTopic)
public class RecommendTopicParams extends RequestParams {

    public RecommendTopicParams(){
        addBodyParameter("userId", MyApp.getInstance().getUser().getUserId()+"");
    }
}
