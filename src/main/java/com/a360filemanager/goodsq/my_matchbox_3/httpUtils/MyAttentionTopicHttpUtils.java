package com.a360filemanager.goodsq.my_matchbox_3.httpUtils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.a360filemanager.goodsq.my_matchbox_3.activity.SelectTopicActivity;
import com.a360filemanager.goodsq.my_matchbox_3.bean.FriendBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.MyAttentionTopicBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.MyAttentionTopicParams;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.ResultCallback;
import com.alibaba.fastjson.JSON;

import org.xutils.x;

/**
 * Created by goodsq on 2016/8/20.
 */
public class MyAttentionTopicHttpUtils {
    public static void getAllAttentionTopic(final CallbackUtils<MyAttentionTopicBean> callback) {
        x.http().post(new MyAttentionTopicParams(), new ResultCallback() {

            @Override
            public void onSuccess(String result) {
                MyAttentionTopicBean bean = JSON.parseObject(result, MyAttentionTopicBean.class);
                Log.e("TAG", "------------" + bean.getList().size());
                if (bean.getResult().toString().equals("0")) {
                   callback.onSuccess(bean);
                }
            }

            @Override
            public void onFinished() {
                callback.onFinish();
            }
        });
    }
}
