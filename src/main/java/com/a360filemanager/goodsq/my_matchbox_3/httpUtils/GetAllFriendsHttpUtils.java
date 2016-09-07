package com.a360filemanager.goodsq.my_matchbox_3.httpUtils;

import com.a360filemanager.goodsq.my_matchbox_3.bean.FriendBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.GetAllFriendsHttpParams;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.ResultCallback;
import com.alibaba.fastjson.JSON;

import org.json.JSONObject;
import org.xutils.x;

/**
 * Created by goodsq on 2016/8/24.
 */
public class GetAllFriendsHttpUtils {

    public static void getAllFriends(final CallbackUtils<FriendBean> callback){
        x.http().post(new GetAllFriendsHttpParams(), new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                FriendBean bean = JSON.parseObject(result,FriendBean.class);
                if (bean.getResult().equals("0")){
                    callback.onSuccess(bean);
                }
            }

            @Override
            public void onFinished() {

            }
        });
    }
}
