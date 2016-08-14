package com.a360filemanager.goodsq.my_matchbox_3.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/8/6.
 */
public abstract class ListItemAdapter<T> extends BaseAdapter {

    protected List<T> mList;
    protected Context mContext;

    public ListItemAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mList = list;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
