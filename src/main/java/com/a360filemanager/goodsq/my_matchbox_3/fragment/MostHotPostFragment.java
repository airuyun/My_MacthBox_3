package com.a360filemanager.goodsq.my_matchbox_3.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.adapter.PostAdapter;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseFragment;
import com.a360filemanager.goodsq.my_matchbox_3.bean.PostBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.PostHttpUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by goodsq on 2016/8/29.
 */
public class MostHotPostFragment extends BaseFragment {

    PullToRefreshListView ptrlv;
    int topicId;

    @Override
    public View getLayout() {
        return View.inflate(getContext(), R.layout.fragment_my_friend,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ptrlv = (PullToRefreshListView) view.findViewById(R.id.like_prlv);
        ptrlv.setOnRefreshListener(listener2);
        topicId = getArguments().getInt("topicId");
    }

    PullToRefreshBase.OnRefreshListener2<ListView> listener2 = new PullToRefreshBase.OnRefreshListener2<ListView>() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
            ptrlv.setRefreshing();
            ptrlv.onRefreshComplete();
            doQuery();

        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

        }
    };

    /*======================================获取所有好友的帖子====================================*/
    public void doQuery() {
        //请求首页数据
        PostHttpUtils.getPostOFTopic(getContext(),topicId,new CallbackUtils<PostBean>() {

            @Override
            public void onSuccess(PostBean postBean) {
                ptrlv.setAdapter(new PostAdapter(getActivity(), postBean.getList()));
                ptrlv.onRefreshComplete();
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
