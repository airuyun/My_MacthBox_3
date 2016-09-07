package com.a360filemanager.goodsq.my_matchbox_3.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.a360filemanager.goodsq.my_matchbox_3.R;

import org.xutils.x;

import java.io.File;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/6.
 */
public class HeadPicAdapter extends ListItemAdapter<File> {


    public HeadPicAdapter(Context context, List<File> list) {
        super(context, list);
    }

    int index = -1;

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int getCount() {
        return mList.size() + 1;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_gv_sethead, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        //设置摄像头
        if (i == 0) {
            holder.itemIv.setImageResource(R.mipmap.icon_camera_pick_ng);
        } else {
            holder.setData(getItem(i - 1));
        }
        //设置选中状态
        if (i == index) {
            holder.itemIv.setSelected(true);
            index = -1;
        } else {
            holder.itemIv.setSelected(false);
        }
        return view;
    }

    class ViewHolder {
        @InjectView(R.id.item_iv)
        ImageView itemIv;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
            int width = mContext.getResources().getDisplayMetrics().widthPixels;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width / 3, width / 3);
            itemIv.setLayoutParams(params);
        }

        public void setData(File item) {
            x.image().bind(itemIv, item.getAbsolutePath());
        }
    }
}
