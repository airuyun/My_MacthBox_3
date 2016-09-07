package com.a360filemanager.goodsq.my_matchbox_3.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.utils.AlbumBitmapCacheHelper;
import com.a360filemanager.goodsq.my_matchbox_3.utils.FileUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.SingleImageModel;

import java.util.ArrayList;

/**
 * Created by goodsq on 2016/8/26.
 */
public class ImageAdapter extends BaseAdapter {

    Context mContext;

    public ImageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    int index = -1;

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int getCount() {
        return FileUtils.getAllImages().size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final GridViewHolder holder;
        int width = mContext.getResources().getDisplayMetrics().widthPixels;
        if (view == null || view.getTag() == null){
            holder = new GridViewHolder();
            view = View.inflate(mContext,R.layout.item_gv_sethead,null);
            view.setLayoutParams(new GridView.LayoutParams(width / 3, width / 3));
            holder.iv_content = (ImageView) view.findViewById(R.id.item_iv);
            view.setTag(holder);
        }else {
            holder = (GridViewHolder) view.getTag();
        }
        //第一个要显示拍摄照片图片
        if (index == -1 && i == 0) {
            view = new ImageView(mContext);
            view.setBackgroundResource(R.mipmap.icon_camera_pick_ng);
            view.setLayoutParams(new GridView.LayoutParams(width / 3, width / 3));
            return view;
        }
        //在此处直接进行处理最好，能够省去其他部分的处理，其他部分直接可以使用原来的数据结构
        if (index == -1)
            i--;
        //设置选中状态
        if (i == index)
            holder.iv_content.setSelected(true);
        else {
            holder.iv_content.setSelected(false);
            index = -1;
        }
        final String path = FileUtils.getAllImages().get(i).path;
        AlbumBitmapCacheHelper.getInstance().addPathToShowlist(path);
        AlbumBitmapCacheHelper.getInstance().getBitmap(path, width / 3, width / 3, new AlbumBitmapCacheHelper.ILoadImageCallback() {
            @Override
            public void onLoadImageCallBack(Bitmap bitmap, String path1, Object... objects) {
                if (bitmap!=null) {
                    BitmapDrawable bd = new BitmapDrawable(mContext.getResources(), bitmap);
                    holder.iv_content.setBackgroundDrawable(bd);
                }else {
                    holder.iv_content.setImageResource(R.mipmap.icon_register_avatar_default);
                }
            }
        }, i);
        return view;
    }
    private class GridViewHolder {
        public ImageView iv_content;
    }
}
