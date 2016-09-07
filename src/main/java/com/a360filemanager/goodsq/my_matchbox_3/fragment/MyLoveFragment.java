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

import butterknife.InjectView;

/**
 * Created by goodsq on 2016/8/16.
 */
/*=============================================喜欢===============================================*/
public class MyLoveFragment extends BaseFragment {

    @InjectView(R.id.love_prlv)
    PullToRefreshListView lovePrlv;

    @Override
    public View getLayout() {
        return View.inflate(getContext(), R.layout.fragment_my_love, null);//布局中只有一个下拉刷新，使用下拉刷新的适配器加载显示的数据
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lovePrlv.setOnRefreshListener(reFreshListener);
    }

    /*==========================================下拉刷新监听==========================================*/
    PullToRefreshBase.OnRefreshListener2<ListView> reFreshListener = new PullToRefreshBase.OnRefreshListener2<ListView>() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
            lovePrlv.setRefreshing();
            lovePrlv.onRefreshComplete();
            getConcernPost();
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

        }
    };

    /*====================================获取关注话题的所有帖子=========== =======================*/
    private void getConcernPost() {
        PostHttpUtils.getAllConcernPost(new CallbackUtils<PostBean>() {
            @Override
            public void onSuccess(PostBean postBean) {
                lovePrlv.setAdapter(new PostAdapter(getActivity(), postBean.getList()));//使用适配器显示帖子
            }

            @Override
            public void onFinish() {
            }
        });
    }
}
