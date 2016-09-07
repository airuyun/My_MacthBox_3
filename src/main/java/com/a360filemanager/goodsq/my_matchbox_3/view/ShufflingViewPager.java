package com.a360filemanager.goodsq.my_matchbox_3.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.a360filemanager.goodsq.my_matchbox_3.R;

/**
 * Created by Administrator on 2016/8/1.
 */
public class ShufflingViewPager extends FrameLayout {

    public ShufflingViewPager(Context context) {
        super(context);
        init();
    }

    public ShufflingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    int index;
    ViewPager pager;
    LinearLayout points;


    //设置适配器
    public void setAdapter(PagerAdapter adapter) {
        if (pointSize == 0)
            throw new IllegalArgumentException("必须先设置点的数量");
        pager.setAdapter(adapter);
        invalidate();
        points.getChildAt(index).setSelected(true);
        // 当前第一页从中心开始
        if (adapter.getCount() == Integer.MAX_VALUE) {
            pager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE % pointSize);
        }
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                points.getChildAt(index % pointSize).setSelected(false);
                points.getChildAt(position % pointSize).setSelected(true);
                index = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    int pointSize;

    public void setPointSize(int pointSize) {
        this.pointSize = pointSize;
        for (int i = 0; i < pointSize; i++) {
            View view = new View(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(25, 5);
            view.setBackgroundResource(R.drawable.pager_points);
            params.setMargins(10, 0, 0, 0);
            view.setLayoutParams(params);
            points.addView(view);
        }
        //将第一个组件 item 做true
        invalidate();
    }

    public void initPoint() {
        GradientDrawable drawable_selecor = new GradientDrawable();
        drawable_selecor.setColor(Color.WHITE);
        GradientDrawable drawable_unSelecor = new GradientDrawable();
        drawable_unSelecor.setColor(Color.GRAY);
        StateListDrawable drawalbe = new StateListDrawable();
    }

    public void startScroll() {
        isScroll = true;
        handler.sendEmptyMessageDelayed(1, 3000);
    }

    boolean isScroll;

    public void stopScroll() {
        isScroll = false;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (isScroll) {
                handler.sendEmptyMessageDelayed(1, 3000);
                pager.setCurrentItem(pager.getCurrentItem() + 1);
            }
        }
    };


    private void init() {
        /**
         * 创建视图
         */
        RelativeLayout layout = new RelativeLayout(getContext());
        pager = new ViewPager(getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        pager.setLayoutParams(params);
        layout.addView(pager);
        points = new LinearLayout(getContext());
        points.setOrientation(LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        params2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params2.setMargins(10, 10, 10, 10);
        points.setLayoutParams(params2);
        layout.addView(points);
        this.addView(layout);
    }
}
