package com.a360filemanager.goodsq.my_matchbox_3.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.bean.PopupBean;

import org.xutils.x;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/6.
 */
public class PopupAdapter extends ListItemAdapter<PopupBean> {

    public PopupAdapter(Context context, List<PopupBean> list) {
        super(context, list);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_lv_popup, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();
        holder.setData(getItem(i));
        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.item_iv_pic)
        ImageView itemIvPic;
        @InjectView(R.id.item_tv_filename)
        TextView itemTvFilename;
        @InjectView(R.id.item_tv_count)
        TextView itemTvCount;
        @InjectView(R.id.item_iv_checked)
        ImageView itemIvChecked;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }

        public void setData(PopupBean bean) {
            x.image().bind(itemIvPic, bean.getFirstPic());
            itemTvFilename.setText(bean.getName());
            itemTvCount.setText(bean.getCount() + "");
            itemIvChecked.setVisibility(bean.isChecked() ? View.VISIBLE : View.INVISIBLE);
        }
    }
}
