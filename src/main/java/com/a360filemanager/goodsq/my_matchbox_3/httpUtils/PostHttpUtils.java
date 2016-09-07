package com.a360filemanager.goodsq.my_matchbox_3.httpUtils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.bean.CommentBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.GoodUserBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.PostBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.GetPostOfTopic;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.GoodListParams;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.MyAttentionPostParams;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.PublishPoastParams;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.PublishPostCommentParams;
import com.a360filemanager.goodsq.my_matchbox_3.httpParams.getCommentListParams;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.ResultCallback;
import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/10.
 */
public class PostHttpUtils {

    //获取我关注的话题中的所有帖子
    public static void getAllConcernPost( final CallbackUtils<PostBean> callback) {
        x.http().post(new MyAttentionPostParams(), new ResultCallback() {
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


    //对帖子进行点赞和取消赞
    public static final void iLike(final boolean b, int postId, final CallbackUtils<Boolean> callback) {
        RequestParams params;
        if (b)
            params = new RequestParams(ServerInterfaceBean.BASE_URL + "/" + ServerInterfaceBean.iLike);
        else
            params = new RequestParams(ServerInterfaceBean.BASE_URL + "/" + ServerInterfaceBean.iNoLike);
        params.addBodyParameter("userId", MyApp.getInstance().getUser().getUserId() + "");
        params.addBodyParameter("friendId", postId + "");
        x.http().post(params, new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(b);
            }

            @Override
            public void onFinished() {

            }
        });
    }


    //获取点赞列表头像
    public static final void getPostGoodList(int postId, final CallbackUtils<GoodUserBean> callback) {
        GoodListParams params = new GoodListParams(postId);
        x.http().post(params, new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                GoodUserBean bean = JSON.parseObject(result, GoodUserBean.class);
                callback.onSuccess(bean);
            }

            @Override
            public void onFinished() {
                callback.onFinish();
            }
        });
    }

    //发布评论
    public static final void publishPostComment(String content, int postId, final CallbackUtils<Boolean> callback) {
        PublishPostCommentParams params = new PublishPostCommentParams(content, postId);
        x.http().post(params, new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jsonObject.optString("result").equals("0")) {
                    callback.onSuccess(true);
                } else
                    callback.onSuccess(false);
            }

            @Override
            public void onFinished() {
                callback.onFinish();
            }
        });
    }

    //获取文章的所有评论
    public static final void getAllComment(int postId, final CallbackUtils<CommentBean> commentBeanCallback) {
        x.http().post(new getCommentListParams(postId), new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                CommentBean bean = JSON.parseObject(result, CommentBean.class);
                commentBeanCallback.onSuccess(bean);
            }

            @Override
            public void onFinished() {
                commentBeanCallback.onFinish();
            }
        });
    }

    //发布帖子（对某个话题发布帖子）
    public static final void getPublishPost(final Context mContext, String path, int topicId, String msg){
        x.http().post(new PublishPoastParams(path, topicId, msg), new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(mContext, "帖子发布成功"+result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinished() {

            }
        });
    }

    //得到某个话题的所有帖子
    public static final void getPostOFTopic(final Context mContext, int topicId, final CallbackUtils<PostBean> callback){
        x.http().post(new GetPostOfTopic(topicId), new ResultCallback() {
            @Override
            public void onSuccess(String result) {
                PostBean bean = JSON.parseObject(result,PostBean.class);
                Log.e("TAG","-----------得到某个话题的所有帖子-----------"+result);
                callback.onSuccess(bean);
                Toast.makeText(mContext, "成功获取帖子中的话题", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinished() {

            }
        });
    }
}
