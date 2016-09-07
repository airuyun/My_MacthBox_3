package com.a360filemanager.goodsq.my_matchbox_3.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.a360filemanager.goodsq.my_matchbox_3.bean.GoodUserBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;

import org.xutils.x;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/8/15.
 */
public class GoodListViewAdapter extends ListItemAdapter<GoodUserBean.ListBean> {

    public GoodListViewAdapter(Context context, List<GoodUserBean.ListBean> list) {
        super(context, list);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GoodUserBean.ListBean bean = getItem(i);
        if (view == null)
            view = new CircleImageView(mContext);
        CircleImageView iv = (CircleImageView) view;
        x.image().bind(iv, ServerInterfaceBean.getImageUrl(bean.getUserImg()));
        return view;
    }
}
