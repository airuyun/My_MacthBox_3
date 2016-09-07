package com.a360filemanager.goodsq.my_matchbox_3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.activity.CreateTopicActivity;
import com.a360filemanager.goodsq.my_matchbox_3.adapter.TopicJointAdapter;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseFragment;
import com.a360filemanager.goodsq.my_matchbox_3.bean.HotTopicBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.RecommendTopicBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.HotTopicHttpUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;
import com.a360filemanager.goodsq.my_matchbox_3.view.ScrollListView;
import com.a360filemanager.goodsq.my_matchbox_3.view.ShufflingViewPager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/12.
 */
public class TopicFragment extends BaseFragment {

    @InjectView(R.id.topic_tv_createTopic)
    TextView topicTvCreateTopic;
    @InjectView(R.id.topic_tv_searchFriends)
    TextView topicTvSearchFriends;
    @InjectView(R.id.topic_tv_classify)
    TextView topicTvClassify;
    @InjectView(R.id.topic_svp)
    ShufflingViewPager topicSvp;
    @InjectView(R.id.topic_slv)
    ScrollListView topicSlv;
    @InjectView(R.id.topic_ptrsv)
    PullToRefreshScrollView topicPtrsv;

    List<HotTopicBean.ListBean> mList = new ArrayList<>();
    TopicJointAdapter adapter;

    @Override
    public View getLayout() {
        return View.inflate(getContext(), R.layout.fragment_topic, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topicPtrsv.setOnRefreshListener(refreshListener);

        topicSvp.setPointSize(10);
        topicSvp.setAdapter(new MyPagerAdapter());
        topicSvp.startScroll();

        adapter = new TopicJointAdapter(getContext(), mList);
        topicSlv.setDividerHeight(0);
        topicSlv.setAdapter(adapter);
        //topicSlv.setOnItemClickListener(itemClickListener);

    }

    @OnClick({R.id.topic_tv_createTopic, R.id.topic_tv_searchFriends, R.id.topic_tv_classify})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.topic_tv_createTopic:
                startActivity(new Intent(getContext(), CreateTopicActivity.class));
                break;
            case R.id.topic_tv_searchFriends:
                break;
            case R.id.topic_tv_classify:
                break;
        }
    }

    /*===========================================下拉刷新=========================================*/
    PullToRefreshBase.OnRefreshListener2 refreshListener = new PullToRefreshBase.OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            mList.clear();
            topicPtrsv.onRefreshComplete();
            doRequest();
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {

        }
    };

    /*=======================================PagerAdapter=========================================*/
    class MyPagerAdapter extends PagerAdapter {

        List<View> mList = new ArrayList<>();

        public MyPagerAdapter() {
            for (int i = 1; i < 11; i++) {
                ImageView iv = new ImageView(getContext());
                int id = 0;
                try {
                    id = R.mipmap.class.getField("tologin_banner" + i).getInt(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                iv.setBackgroundResource(id);
                mList.add(iv);
            }
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position % 10));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position % 10));
            return mList.get(position % 10);
        }
    }

    /*====================================获取要在适配器显示的数据================================*/
    private void doRequest() {

        HotTopicHttpUtils.getAllHotTopic(new CallbackUtils<HotTopicBean>() {//获取所有最热门话题

            @Override
            public void onSuccess(HotTopicBean hotTopicBean) {
                int count = hotTopicBean.getList().size() > 5 ? 5 : hotTopicBean.getList().size();
                adapter.setHot(hotTopicBean);
                for (int i = 0; i < count; i++) {
                    HotTopicBean.ListBean bean = hotTopicBean.getList().get(i);
                    bean.setType(HotTopicBean.ListBean.TYPE_HOT);
                    mList.add(bean);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFinish() {

            }
        });

        HotTopicHttpUtils.getRecommendTopic(new CallbackUtils<HotTopicBean>() {//获取所有新话题推荐
            @Override
            public void onSuccess(HotTopicBean hotTopicBean) {
                int count = hotTopicBean.getList().size() > 5 ? 5 : hotTopicBean.getList().size();
                adapter.setRecommend(hotTopicBean);
                for (int i = 0; i < count; i++) {
                    HotTopicBean.ListBean bean = hotTopicBean.getList().get(i);
                    bean.setType(HotTopicBean.ListBean.TYPE_RECOMMEND);
                    mList.add(bean);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFinish() {

            }
        });

        HotTopicHttpUtils.getUpdateTopic(new CallbackUtils<HotTopicBean>() {//获取有更新的话题

            @Override
            public void onSuccess(HotTopicBean hotTopicBean) {
                int count = hotTopicBean.getList().size() > 5 ? 5 : hotTopicBean.getList().size();
                adapter.setUpdate(hotTopicBean);
                for (int i = 0; i < count; i++) {
                    HotTopicBean.ListBean bean = hotTopicBean.getList().get(i);
                    bean.setType(HotTopicBean.ListBean.TYPE_UPDATE);
                    mList.add(bean);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    };
}
