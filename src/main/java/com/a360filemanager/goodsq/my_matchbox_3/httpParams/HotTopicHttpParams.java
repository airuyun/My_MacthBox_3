package com.a360filemanager.goodsq.my_matchbox_3.httpParams;

import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

/**
 * Created by goodsq on 2016/8/16.
 */

@HttpRequest(host = ServerInterfaceBean.BASE_URL,path = ServerInterfaceBean.HOT_TOPIC)
public class HotTopicHttpParams extends RequestParams {
    public HotTopicHttpParams(){
        this.addBodyParameter("userId","1");
    }
}
