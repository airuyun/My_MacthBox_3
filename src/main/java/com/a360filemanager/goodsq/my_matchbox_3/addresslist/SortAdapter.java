package com.a360filemanager.goodsq.my_matchbox_3.addresslist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
public abstract class SortAdapter<T extends IGetString> extends BaseAdapter {

    protected List<T> mList = new ArrayList<>();
    protected Context mContext;

    public SortAdapter(Context context, List<T> list) {
        this.mList.addAll(list);
        this.mContext = context;
        Collections.sort(mList, new Comparator<T>() {
            @Override
            public int compare(T lhs, T rhs) {
                return lhs.getString().trim().substring(0, 1).toUpperCase().compareToIgnoreCase(rhs.getString().trim().substring(0, 1).toUpperCase());
            }
        });
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GroupHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_sortlist, null);
            holder = new GroupHolder(convertView);
            holder.content.addView(getView());
            convertView.setTag(holder);
        } else
            holder = (GroupHolder) convertView.getTag();
        String name = mList.get(position).getString().trim().substring(0, 1).toUpperCase();
        if (name.matches("[^A-Z]"))
            name = "#";
        if (position == getFirstIndexFromAdapter(name) || position == 0) {
            //如果相等，显示
            holder.sort_tv.setVisibility(View.VISIBLE);
            holder.sort_tv.setText(name);
        } else
            holder.sort_tv.setVisibility(View.GONE);
        setData(position, holder.content);
        return convertView;
    }

    //获取当前的字母在数据的第一个位置  // AC AD AE AF A....  0   30--60  30   30 31 32
    public int getFirstIndexFromAdapter(String name) {

        for (int i = 0; i < mList.size(); i++) {
            String s = mList.get(i).getString().substring(0, 1).toUpperCase();//H
            if (name.equals(s))
                return i;
        }
        return -1;
    }


    public abstract View getView();

    public abstract void setData(int position, View layout);

    class GroupHolder {
        TextView sort_tv;
        FrameLayout content;

        public GroupHolder(View convertView) {
            sort_tv = (TextView) convertView.findViewById(R.id.sort_tv);
            content = (FrameLayout) convertView.findViewById(R.id.content);
        }

    }
}
