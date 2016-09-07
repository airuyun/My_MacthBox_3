package com.a360filemanager.goodsq.my_matchbox_3.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by goodsq on 2016/8/24.
 */
public class ScrollViewWithListView extends ListView {
    public ScrollViewWithListView(Context context) {
        super(context);
    }

    public ScrollViewWithListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
    }
}
