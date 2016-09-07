package com.a360filemanager.goodsq.my_matchbox_3.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.activity.PostDeatilActivity;
import com.a360filemanager.goodsq.my_matchbox_3.bean.PostBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.AttentionFriendHttpUtils;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.PostHttpUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.TimeUtils;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/11.
 */
public class PostAdapter extends ListItemAdapter<PostBean.ListBean> {

    public PostAdapter(Context context, List<PostBean.ListBean> list) {
        super(context, list);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.post_layout, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.setData(mList.get(i));
        return view;
    }

    /*============================================================================================*/
    class ViewHolder {
        @InjectView(R.id.post_tv_topicname)
        TextView postTvTopicname;
        @InjectView(R.id.post_tv_add)
        TextView postTvAdd;
        @InjectView(R.id.post_civ_head)
        ImageView postCivHead;
        @InjectView(R.id.post_iv_share)
        ImageView postIvShare;
        @InjectView(R.id.post_tv_nickname)
        TextView postTvNickname;
        @InjectView(R.id.post_iv_content)
        ImageView postIvContent;
        @InjectView(R.id.post_tv_content)
        TextView postTvContent;
        @InjectView(R.id.post_tv_is15line)
        TextView postTvIs15line;
        @InjectView(R.id.post_tv_time)
        TextView postTvTime;
        @InjectView(R.id.post_cb_like)
        CheckBox postCbLike;
        @InjectView(R.id.post_tv_comment)
        TextView postTvComment;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }

        public void setData(final PostBean.ListBean bean) {
            //话题
            postTvTopicname.setText(bean.getTopicName());
            //用户头像
            if (!bean.getImgUrl().equals("")) {
                x.image().bind(postCivHead, ServerInterfaceBean.getImageUrl(bean.getImgUrl()));
            } else {
                postCivHead.setImageResource(R.mipmap.icon_register_avatar_default);
            }
            //用户昵称
            postTvNickname.setText(bean.getUserName());
            //设置添加关注
            if (bean.getIsAction() == 0) {//0为未关注， !0表示已经关注
                postTvAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AttentionFriendHttpUtils.attentionFriend(bean.getUserId());
                        bean.setIsAction(1);
                        PostAdapter.this.notifyDataSetChanged();
                    }
                });
            } else {
                postTvAdd.setEnabled(false);
                postTvAdd.setText("已关注");
                postTvAdd.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);//设置文本与图片相对位置时
            }
            //判断是否有图像内容 。不要用type
            if (bean.getPhotoList() != null && bean.getPhotoList().size() > 0) {
                postIvContent.setVisibility(View.VISIBLE);
                ImageOptions options = new ImageOptions.Builder().setCrop(false).build();//xutils中的类，不裁剪
                x.image().bind(postIvContent, ServerInterfaceBean.getImageUrl(bean.getPhotoList().get(0).getImgUrl()), options);
            } else {
                postIvContent.setVisibility(View.GONE);
        }
            //文字，单行 居中显示， 多行 向左对齐，超过15行，显示下面的更多，超过15行，只显示15行
            Log.e("TAG","----===----shen----===----"+bean.getMsg());
            postTvContent.setText(bean.getMsg());
            postTvIs15line.setVisibility(View.GONE);
            if (postTvContent.getLineCount() == 1) {
                postTvContent.setGravity(Gravity.CENTER);
            } else {
                postTvContent.setGravity(Gravity.LEFT);
                if (postTvContent.getLineCount() > 15) {
                    postTvIs15line.setVisibility(View.VISIBLE);
                    postTvIs15line.setTag(postTvContent.getLineCount());
                    postTvContent.setLines(15);
                    postTvIs15line.setOnClickListener(showmoreListener);
                }
            }
            //时间
            postTvTime.setText(TimeUtils.formatTime(bean.getCreateDate()));

            //点赞
            postCbLike.setChecked(bean.getIsTop() == 0 ? false : true);
            postCbLike.setText(bean.getTopCount() + "");
            postCbLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean b = bean.getIsTop() == 0 ? true : false;
                    postCbLike.setChecked(b);
                    bean.setTopCount(b ? bean.getTopCount() + 1 : bean.getTopCount() - 1);
                    bean.setIsTop(b ? 1 : 0);
                    PostAdapter.this.notifyDataSetChanged();
                    //请求数据
                    PostHttpUtils.iLike(b, bean.getFriendId(), new CallbackUtils<Boolean>() {

                        @Override
                        public void onSuccess(Boolean aBoolean) {

                        }

                        @Override
                        public void onFinish() {

                        }
                    });
                }
            });
            postTvComment.setText(bean.getDiscussCount() + "");
            postTvComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Activity activity = (Activity) mContext;
                    activity.startActivityForResult(new Intent(mContext, PostDeatilActivity.class)
                            .putExtra("bean", bean), bean.getFriendId());
                }
            });
        }

        View.OnClickListener showmoreListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = postTvIs15line.getText().toString();
                int lines = (int) view.getTag();
                if (text.equals("显示更多")) {
                    ObjectAnimator.ofInt(postTvContent, "lines", 15, lines).setDuration(300)
                            .start();
                    postTvIs15line.setText("收起");
                } else {
                    ObjectAnimator.ofInt(postTvContent, "lines", lines, 15).setDuration(300)
                            .start();
                    postTvIs15line.setText("显示更多");
                }
            }
        };

    }
}
