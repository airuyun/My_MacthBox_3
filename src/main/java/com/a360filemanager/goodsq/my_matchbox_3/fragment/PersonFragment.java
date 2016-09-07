package com.a360filemanager.goodsq.my_matchbox_3.fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.adapter.MyFragmentPagerAdapter;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseFragment;
import com.a360filemanager.goodsq.my_matchbox_3.view.ScrollingView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goodsq on 2016/8/12.
 */
public class PersonFragment extends BaseFragment{

    ScrollingView sv;
    TextView hv;
    LinearLayout ll;
    ViewPager vp;
    List<Fragment> mList = new ArrayList<>();

    @Override
    public View getLayout() {
        return View.inflate(getContext(), R.layout.fragment_person,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sv = (ScrollingView) view.findViewById(R.id.sv);
        hv = (TextView) view.findViewById(R.id.hideview);
        ll = (LinearLayout) view.findViewById(R.id.mine_tab_layout);
        vp = (ViewPager) view.findViewById(R.id.mine_vp);
        sv.setOnScrollChangedListener(scrollChangedListener);
        //获取Sv的观察者
        sv.getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);
        mList.add(new ListFragment());
        mList.add(new ListFragment());
        mList.add(new ListFragment());
        vp.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(),mList));
    }

    ScrollingView.OnScrollChangedListener scrollChangedListener = new ScrollingView.OnScrollChangedListener() {
        @Override
        public void onScroll(int scorllY) {
            Log.e("TAG","-----------------YYY"+scorllY);
            int height = Math.max(hv.getTop(),scorllY);
            //移动我们的tablayout
            ll.setTranslationY(height);

        }
    };

    ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            //将当前滑动的Y轴传递给监听
            scrollChangedListener.onScroll(sv.getScrollY());
        }
    };
}
