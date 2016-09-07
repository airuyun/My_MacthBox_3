package com.a360filemanager.goodsq.my_matchbox_3.httpUtils;

import com.a360filemanager.goodsq.my_matchbox_3.bean.PostBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.AttentionFriendParams;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.GetFriendPostParams;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.ResultCallback;
import com.alibaba.fastjson.JSON;

import org.xutils.x;

/**
 * Created by Administrator on 2016/8/11.
 */
public class AttentionFriendHttpUtils {
    //关注好友
    //取消关注
    public static final void attentionFriend(int friendId) {
        AttentionFriendParams params = new AttentionFriendParams(friendId);
        x.http().post(params, new ResultCallback() {
            @Override
            public void onSuccess(String result) {
            }

            @Override
            public void onFinished() {

            }
        });
    }

    //获取所有好友的帖子
    public static final void getFirendAllPost(final CallbackUtils<PostBean> callback) {
        x.http().post(new GetFriendPostParams(), new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                PostBean bean = JSON.parseObject(result, PostBean.class);
                callback.onSuccess(bean);
            }

            @Override
            public void onFinished() {
                callback.onFinish();
            }
        });
    }
}
