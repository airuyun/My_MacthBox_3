package com.a360filemanager.goodsq.my_matchbox_3.httpParams;

import android.util.Log;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

/**
 * Created by goodsq on 2016/8/20.
 */
@HttpRequest(host = ServerInterfaceBean.BASE_URL,path = ServerInterfaceBean.MyAttentionTopic)
public class MyAttentionTopicParams extends RequestParams {

    public MyAttentionTopicParams(){
        Log.e("TAG","-------------UserId----------"+MyApp.getInstance().getUser().getUserId());
        this.addBodyParameter("userId", MyApp.getInstance().getUser().getUserId() + "");//通过用户ID获取此用户已关注的所有话题
    }

}
