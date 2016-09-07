package com.a360filemanager.goodsq.my_matchbox_3.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.addresslist.SortAdapter;
import com.a360filemanager.goodsq.my_matchbox_3.bean.FriendBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;
import com.a360filemanager.goodsq.my_matchbox_3.view.CircleImageView;

import org.xutils.x;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/19.
 */
public class AttentionAdapter extends SortAdapter<FriendBean.FirendsBean> {

      public AttentionAdapter(Context context, List<FriendBean.FirendsBean> list) {
        super(context, list);
    }

    //就是返回布局
    @Override
    public View getView() {
        return View.inflate(mContext, R.layout.item_contacts, null);
    }

    @Override
    public void setData(int position, View layout) {
        ViewHolder holder = new ViewHolder(layout);
        holder.itemContactsTvId.setText("ID:" + mList.get(position).getUserId());
        holder.itemContactsTvNickname.setText(mList.get(position).getUserName());
        if (mList.get(position).getUrl() != null && !mList.get(position).getUrl().equals("")) {
            x.image().bind(holder.itemContactsCivPicture, ServerInterfaceBean.getImageNewUrl(mList.get(position).getUrl()));
        }
        else {
            holder.itemContactsCivPicture.setImageResource(R.mipmap.icon_register_avatar_default);
        }
    }

    static class ViewHolder {
        @InjectView(R.id.item_contacts_civ_picture)
        CircleImageView itemContactsCivPicture;
        @InjectView(R.id.item_contacts_tv_nickname)
        TextView itemContactsTvNickname;
        @InjectView(R.id.item_contacts_tv_id)
        TextView itemContactsTvId;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
