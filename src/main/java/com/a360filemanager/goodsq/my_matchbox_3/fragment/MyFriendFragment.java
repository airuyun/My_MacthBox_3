package com.a360filemanager.goodsq.my_matchbox_3.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.adapter.PostAdapter;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseFragment;
import com.a360filemanager.goodsq.my_matchbox_3.bean.PostBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.AttentionFriendHttpUtils;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.PostHttpUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;

import butterknife.InjectView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by goodsq on 2016/8/16.
 */

/*================================================好友============================================*/
public class MyFriendFragment extends BaseFragment {
    @InjectView(R.id.like_prlv)
    PullToRefreshListView likePrlv;

    @Override
    public View getLayout() {
        return View.inflate(getContext(), R.layout.fragment_my_friend, null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        likePrlv.setOnRefreshListener(reFreshListenernew);
    }

    PullToRefreshBase.OnRefreshListener2<ListView> reFreshListenernew = new PullToRefreshBase.OnRefreshListener2<ListView>() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
            likePrlv.setRefreshing();
            likePrlv.onRefreshComplete();
            friendDoQuery();
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

        }
    };

    /*======================================获取所有好友的帖子====================================*/
    public void friendDoQuery() {
        //请求首页数据
        AttentionFriendHttpUtils.getFirendAllPost(new CallbackUtils<PostBean>() {

            @Override
            public void onSuccess(PostBean postBean) {
                likePrlv.setAdapter(new PostAdapter(getActivity(), postBean.getList()));
                likePrlv.onRefreshComplete();
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
