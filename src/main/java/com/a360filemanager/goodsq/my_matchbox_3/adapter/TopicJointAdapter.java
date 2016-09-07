package com.a360filemanager.goodsq.my_matchbox_3.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.activity.EditPostActivity;
import com.a360filemanager.goodsq.my_matchbox_3.activity.MoreTopicListActivity;
import com.a360filemanager.goodsq.my_matchbox_3.activity.PostDeatilActivity;
import com.a360filemanager.goodsq.my_matchbox_3.activity.PublishTopicActivity;
import com.a360filemanager.goodsq.my_matchbox_3.bean.HotTopicBean;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/16.
 */
public class TopicJointAdapter extends ListItemAdapter<HotTopicBean.ListBean> {

    int type = -1;
    HashMap<String, HotTopicBean> hashMap;


    public TopicJointAdapter(Context context, List<HotTopicBean.ListBean> list) {
        super(context, list);
        hashMap = new HashMap<>();
    }

    public void setHot(HotTopicBean hot) {
        hashMap.put("热门话题", hot);
    }

    public void setRecommend(HotTopicBean recommend) {
        hashMap.put("推荐话题", recommend);
    }

    public void setUpdate(HotTopicBean update) {
        hashMap.put("有更新的话题", update);
    }




    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_topic, null);
            holder = new ViewHolder(view);
        } else
            holder = (ViewHolder) view.getTag();
        //需要判断上一次的type和当前view的type是否相同
        final HotTopicBean.ListBean bean = getItem(i);
        if (type == bean.getType()) {
            holder.itemTopicRl.setVisibility(View.GONE);
        } else {
            holder.itemTopicRl.setVisibility(View.VISIBLE);
            //holder.itemTpicTvMore.setOnClickListener(onClickListener);
            holder.itemTpicTvMore.setTag(bean.getType());
            holder.item_topic_tv_title.setText(HotTopicBean.ListBean.titles[bean.getType()]);
            holder.itemTpicTvMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String key = HotTopicBean.ListBean.titles[bean.getType()];
                    HotTopicBean bean = hashMap.get(key);
                    Intent intent = new Intent(mContext, MoreTopicListActivity.class);
                    intent.putExtra("title", key);
                    intent.putExtra("bean", bean);
                    mContext.startActivity(intent);
                }
            });
        }
        type = bean.getType();
        holder.itemTopicTvName.setText(bean.getName());
        holder.itemTopicTvSee.setText(bean.getSeeCount() + "次浏览");
        holder.item_topic_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PublishTopicActivity.class);
                intent.putExtra("topic", bean);
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int type = (int) view.getTag();
        }
    };

    static class ViewHolder {
        @InjectView(R.id.item_tpic_tv_more)
        TextView itemTpicTvMore;
        @InjectView(R.id.item_topic_tv_title)
        TextView item_topic_tv_title;
        @InjectView(R.id.item_topic_rl)
        RelativeLayout itemTopicRl;
        @InjectView(R.id.item_topic_tv_name)
        TextView itemTopicTvName;
        @InjectView(R.id.item_topic_tv_see)
        TextView itemTopicTvSee;
        @InjectView(R.id.item_topic_layout)
        RelativeLayout item_topic_layout;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
            view.setTag(this);
        }
    }
}
