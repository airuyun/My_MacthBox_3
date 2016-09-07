package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.adapter.MyFragmentPagerAdapter;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.bean.HotTopicBean;
import com.a360filemanager.goodsq.my_matchbox_3.fragment.MostHotPostFragment;
import com.a360filemanager.goodsq.my_matchbox_3.fragment.MostNewPostFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/29.
 */
public class PublishTopicActivity extends BaseActvity {


    @InjectView(R.id.publish_topic_iv_goBack)
    ImageView publishTopicIvGoBack;
    @InjectView(R.id.publish_topic_tv_topic)
    TextView publishTopicTvTopic;
    @InjectView(R.id.publish_topic_iv_more)
    ImageView publishTopicIvMore;
    @InjectView(R.id.publish_topic_iv_attention)
    ImageView publishTopicIvAttention;
    @InjectView(R.id.rl)
    RelativeLayout rl;
    @InjectView(R.id.publish_topic_iv_picture)
    ImageView publishTopicIvPicture;
    @InjectView(R.id.publish_topic_tv)
    TextView publishTopicTv;
    @InjectView(R.id.publish_topic_rl_edit_post)
    RelativeLayout publishTopicRlEditPost;
    @InjectView(R.id.publish_topic_rl_publish_post)
    RelativeLayout publishTopicRlPublishPost;
    @InjectView(R.id.publish_topic_vp)
    ViewPager publishTopicVp;

    MostHotPostFragment mostHotPostFragment;
    MostNewPostFragment mostNewPostFragment;
    List<Fragment> mList = new ArrayList<>();
    HotTopicBean.ListBean bean;

    @Override
    public int getLayout() {
        return R.layout.activity_publish_topic;
    }

    @Override
    public void init() {
        Intent intent = getIntent();
        bean = (HotTopicBean.ListBean) intent.getSerializableExtra("topic");
        publishTopicTvTopic.setText(bean.getName());
        mostHotPostFragment = new MostHotPostFragment();
        mostNewPostFragment = new MostNewPostFragment();
        mList.add(mostHotPostFragment);
        mList.add(mostNewPostFragment);
        publishTopicVp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),mList));
        Bundle bundle = new Bundle();
        bundle.putInt("topicId",bean.getTopicId());
        mostHotPostFragment.setArguments(bundle);
    }

    @OnClick({R.id.publish_topic_iv_goBack, R.id.publish_topic_tv_topic, R.id.publish_topic_iv_more, R.id.publish_topic_iv_attention, R.id.rl, R.id.publish_topic_iv_picture, R.id.publish_topic_tv, R.id.publish_topic_rl_edit_post})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.publish_topic_iv_goBack:
                finish();
                break;
            case R.id.publish_topic_iv_more:
                break;
            case R.id.publish_topic_iv_attention:
                break;
            case R.id.publish_topic_iv_picture:
                break;
            case R.id.publish_topic_rl_edit_post:
                Intent intent = new Intent(this, EditPostActivity.class);
                intent.putExtra("topicId",bean.getTopicId());
                startActivity(intent);
                break;
        }
    }
}
