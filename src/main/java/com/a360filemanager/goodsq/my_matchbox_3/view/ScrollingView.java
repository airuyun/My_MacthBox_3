package com.a360filemanager.goodsq.my_matchbox_3.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/8/29.
 */
public class ScrollingView extends ScrollView {
    public ScrollingView(Context context) {
        super(context);
    }

    public ScrollingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollChangedListener != null) {
            onScrollChangedListener.onScroll(t);
        }
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.onScrollChangedListener = onScrollChangedListener;
    }

    OnScrollChangedListener onScrollChangedListener;

    public interface OnScrollChangedListener {
        void onScroll(int scorllY);
    }
}
