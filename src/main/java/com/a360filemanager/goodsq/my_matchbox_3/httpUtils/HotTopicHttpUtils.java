package com.a360filemanager.goodsq.my_matchbox_3.httpUtils;

import android.util.Log;

import com.a360filemanager.goodsq.my_matchbox_3.bean.CreateNewTopicHttpParams;
import com.a360filemanager.goodsq.my_matchbox_3.bean.HotTopicBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.NewTopicBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.RecommendTopicBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.ConcernHotTopicHttpParams;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.HotTopicHttpParams;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.NewTopicHttpParams;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.RecommendTopicParams;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.UpdateTopicHttpParams;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.ResultCallback;
import com.alibaba.fastjson.JSON;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;

/**
 * Created by goodsq on 2016/8/16.
 */
public class HotTopicHttpUtils {

    public static void getAllHotTopic(final CallbackUtils<HotTopicBean> callback) {
        x.http().post(new HotTopicHttpParams(), new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "------------------所有话题" + result);
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
                Log.e("TAG", "------------------关注的话题" + result);
            }

            @Override
            public void onFinished() {
            }
        });
    }

    public static void getNewTopic(final CallbackUtils<HotTopicBean> callback){
        x.http().post(new NewTopicHttpParams(), new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "------------------新话题" + result);
                HotTopicBean bean = JSON.parseObject(result,HotTopicBean.class);
                callback.onSuccess(bean);
            }

            @Override
            public void onFinished() {

            }
        });
    }

    public static void getUpdateTopic(final CallbackUtils<HotTopicBean> callback){
        x.http().post(new UpdateTopicHttpParams(), new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "------------------有更新的新话题" + result);
                HotTopicBean bean = JSON.parseObject(result,HotTopicBean.class);
                callback.onSuccess(bean);
            }

            @Override
            public void onFinished() {

            }
        });
    }

    public static void getRecommendTopic(final CallbackUtils<HotTopicBean> callback){
        x.http().post(new RecommendTopicParams(), new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "------------------推荐的新话题" + result);
                HotTopicBean bean = JSON.parseObject(result,HotTopicBean.class);
                callback.onSuccess(bean);
            }

            @Override
            public void onFinished() {

            }
        });
    }

    public static void createNewTopic(String topicName, final CallbackUtils<Boolean> callbackUtils){
        x.http().post(new CreateNewTopicHttpParams(topicName), new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "------------------创建新话题" + result);
                JSONObject object = null;
                try {
                    object = new JSONObject(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (object.optString("result").equals("0")) {
                    callbackUtils.onSuccess(true);
                }else{
                    callbackUtils.onSuccess(false);
                }
            }

            @Override
            public void onFinished() {

            }
        });
    }


}
