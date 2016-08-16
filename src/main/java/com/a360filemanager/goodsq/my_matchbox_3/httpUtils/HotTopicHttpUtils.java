package com.a360filemanager.goodsq.my_matchbox_3.httpUtils;

import com.a360filemanager.goodsq.my_matchbox_3.bean.HotTopicBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.ConcernHotTopicHttpParams;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.HotTopicHttpParams;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.ResultCallback;
import com.alibaba.fastjson.JSON;

import org.xutils.x;

/**
 * Created by goodsq on 2016/8/16.
 */
public class HotTopicHttpUtils {

    public static void getAllHotTopic(final CallbackUtils<HotTopicBean> callback) {
        x.http().post(new HotTopicHttpParams(), new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                HotTopicBean bean = JSON.parseObject(result, HotTopicBean.class);
                callback.onSuccess(bean);
            }

            @Override
            public void onFinished() {

            }
        });
    }

    public static void concernedHotTopic(int topicId) {
        x.http().post(new ConcernHotTopicHttpParams(topicId), new ResultCallback() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
