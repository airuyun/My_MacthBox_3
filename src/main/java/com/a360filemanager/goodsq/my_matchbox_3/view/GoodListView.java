package com.a360filemanager.goodsq.my_matchbox_3.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;

/**
 * Created by Administrator on 2016/8/12.
 */
public class GoodListView extends LinearLayout {

    ListAdapter adapter;

    float width, height;

    public GoodListView(Context context) {
        super(context);
        init();
        width = 80;
        height = 80;
    }

    public GoodListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GoodListView);
        width = a.getDimension(R.styleable.GoodListView_width, 40);
        height = a.getDimension(R.styleable.GoodListView_height, 40);
    }

    public void init() {
        setOrientation(HORIZONTAL);
    }


    public void drawLayout() {
        //使用适配器
        this.removeAllViews();
        if (adapter.getCount() == 0)
            return;
        LayoutParams params = new LayoutParams((int) width, (int) height);
        ImageView like = new ImageView(getContext());
        like.setImageResource(R.mipmap.icon_post_like);
        like.setScaleType(ImageView.ScaleType.FIT_CENTER);
        like.setPadding(20, 20, 20, 20);
        like.setLayoutParams(params);
        this.addView(like);
        int displayWidth = getResources().getDisplayMetrics().widthPixels;
        int count = (int) (displayWidth / width);
        //已减去桃心
        count--;
        //如果可以放的数量大于已有的数量
        if (count >= adapter.getCount()) {
            for (int i = 0; i < adapter.getCount(); i++) {
                View layout = adapter.getView(i, null, null);
                this.addView(layout);
                layout.setLayoutParams(params);
            }
        } else {
            for (int i = 0; i < count - 1; i++) {
                View layout = adapter.getView(i, null, null);
                this.addView(layout);
                layout.setLayoutParams(params);
            }
            TextView tv = new TextView(getContext());
            tv.setText(adapter.getCount() - count - 1);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(R.drawable.goodlist_count_background);
            tv.setTextColor(Color.rgb(0xff, 0x64, 0x64));
            tv.setLayoutParams(params);
            //在代码中怎么设置一个圆角drawable
            this.addView(tv);
            invalidate();
        }
    }


    public ListAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ListAdapter adapter) {
        this.adapter = adapter;
        //从adapter中获取数据
        drawLayout();
    }
}
