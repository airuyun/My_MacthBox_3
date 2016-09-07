package com.a360filemanager.goodsq.my_matchbox_3.httpParams;

import android.util.Log;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

/**
 * Created by Administrator on 2016/8/10.
 */
@HttpRequest(host = ServerInterfaceBean.BASE_URL, path = ServerInterfaceBean.MyAttentionPost)
public class MyAttentionPostParams extends RequestParams {

    public MyAttentionPostParams() {
        this.addBodyParameter("userId", MyApp.getInstance().getUser().getUserId() + "");//通过用户ID获取此用户已关注话题的所有帖子
    }
}
