package com.a360filemanager.goodsq.my_matchbox_3.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.activity.SearchActivity;
import com.a360filemanager.goodsq.my_matchbox_3.adapter.MyFragmentPagerAdapter;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseFragment;
import com.a360filemanager.goodsq.my_matchbox_3.bean.PostBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.AttentionFriendHttpUtils;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.PostHttpUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/12.
 */
@TargetApi(Build.VERSION_CODES.M)
public class FriendFragment extends BaseFragment {
    @InjectView(R.id.match_rb_firend)
    RadioButton matchRbFirend;
    @InjectView(R.id.match_rg)
    RadioGroup matchRg;
    @InjectView(R.id.match_vp)
    ViewPager matchVp;

    MyFragmentPagerAdapter adapter;
    List<Fragment> fragments = new ArrayList<>();
    MyFriendFragment myFriendFragment;
    MyLoveFragment myLoveFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myFriendFragment = new MyFriendFragment();
        myLoveFragment = new MyLoveFragment();
        fragments.add(myFriendFragment);
        fragments.add(myLoveFragment);
        adapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragments);
    }

    @Override
    public View getLayout() {
        return View.inflate(getContext(), R.layout.fragment_friend, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        matchRbFirend.setChecked(true);
        matchVp.setAdapter(adapter);
        matchVp.setOnPageChangeListener(pageChangeListener);
    }

    @OnClick({R.id.match_rb_firend, R.id.match_rb_like, R.id.match_rg, R.id.match_iv_search, R.id.match_vp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.match_rb_firend:
                matchVp.setCurrentItem(0);
                break;
            case R.id.match_rb_like:
                matchVp.setCurrentItem(1);
                break;
            case R.id.match_iv_search:
                startActivity(new Intent(getContext(), SearchActivity.class));
                break;
        }
    }

    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            matchRg.check(position % 2 == 0 ? R.id.match_rb_firend : R.id.match_rb_like);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
