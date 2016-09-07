package com.a360filemanager.goodsq.my_matchbox_3.httpParams;

import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

/**
 * Created by Administrator on 2016/8/15.
 */
@HttpRequest(host = ServerInterfaceBean.BASE_URL, path = ServerInterfaceBean.GetGoodList)
public class GoodListParams extends RequestParams {

    public GoodListParams(int postId) {
        this.addBodyParameter("friendCircle.id", postId + "");
    }
}
