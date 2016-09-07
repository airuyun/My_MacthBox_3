package com.a360filemanager.goodsq.my_matchbox_3.httpParams;

import android.util.Log;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

import java.io.File;

/**
 * Created by goodsq on 2016/8/29.
 */

/*===========================================发布帖子=============================================*/
@HttpRequest(host = ServerInterfaceBean.BASE_URL,path = ServerInterfaceBean.PublishPost)
public class PublishPoastParams extends RequestParams {
    public PublishPoastParams(String path,int topicId,String msg){
        Log.e("TAG","----------------id"+MyApp.getInstance().getUser().getUserId());
        addBodyParameter("friendCircle.user.id", MyApp.getInstance().getUser().getUserId()+"");//用户ID
        addBodyParameter("friendCircle.topic.id",topicId+"");
        addBodyParameter("friendCircle.msg",msg);
        if (path == null || path.equals("")){
            addBodyParameter("type","1");//发布纯文本帖子
        }else {
            addBodyParameter("type","2");//发布带图帖子
            addBodyParameter("pic",new File(path));
        }

    }
}
