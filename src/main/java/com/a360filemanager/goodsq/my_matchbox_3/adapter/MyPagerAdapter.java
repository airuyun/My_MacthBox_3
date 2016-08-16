package com.a360filemanager.goodsq.my_matchbox_3.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goodsq on 2016/8/8.
 * <p/>
 * 经常出现IllegalStateException:  You must call removeView() on the child's parent first.异常
 * 对PagerAdapter的使用不熟练
 */
public class MyPagerAdapter extends PagerAdapter {

    Context mContext;
    List<View> mList = new ArrayList<>();

    public MyPagerAdapter(Context mContext) {
        this.mContext = mContext;
        init();
    }

    private void init() {

        for (int i = 0; i < 5; i++) {
            View view = View.inflate(mContext, R.layout.text_vp_main, null);//出现问题的地方,把这行代码放for循环外了,猪一样蠢;这一行放for外面,只创建一个对象
            TextView tvAbove = (TextView) view.findViewById(R.id.main_sv_tvTop);
            TextView tvBelow = (TextView) view.findViewById(R.id.main_sv_tvBottom);
            tvAbove.setText(mContext.getResources().getStringArray(R.array.main_textTop)[i]);
            tvBelow.setText(mContext.getResources().getStringArray(R.array.main_textBottom)[i]);
            mList.add(view);
        }
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//instantiate 是实例化的意思
        container.addView(mList.get(position % 5));
        return mList.get(position % 5);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position % 5));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
