package com.a360filemanager.goodsq.my_matchbox_3.httpParams;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

/**
 * Created by goodsq on 2016/8/23.
 */
@HttpRequest(host = ServerInterfaceBean.BASE_URL,path = ServerInterfaceBean.UpdateTopicList)
public class UpdateTopicHttpParams extends RequestParams {
    public UpdateTopicHttpParams(){
        this.addBodyParameter("userId", MyApp.getInstance().getUser().getUserId()+"");
    }
}
