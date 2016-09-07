package com.a360filemanager.goodsq.my_matchbox_3.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.bean.CommentBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;

import org.xutils.x;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/8/15.
 */
public class CommentAdapter extends ListItemAdapter<CommentBean.ListBean> {

    public CommentAdapter(Context context, List<CommentBean.ListBean> list) {
        super(context, list);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_comment_list, null);
            holder = new ViewHolder(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        CommentBean.ListBean bean = getItem(i);
        x.image().bind(holder.civUserimg, ServerInterfaceBean.getImageUrl(bean.getUserImg()));
        String text = bean.getContent();
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        int start = 0;
        int count = 0;
        while (start != -1) {
            //count != 第几个
            //count == 从第几个字符开始
            start = text.indexOf("【", count);//[static_f_000][start_f_001];
            count = start + 1;
            if (start != -1) {
                int end = text.indexOf("】", count);
                int id = 0;
                try {
                    id = R.drawable.class.getField(text.substring(start + 1, end)).getInt(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                ImageSpan span = new ImageSpan(mContext, id);
                builder.setSpan(span, start, end + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        holder.tvContent.setText(builder);
        holder.tvTime.setText(bean.getCreateDate());
        holder.tvUsername.setText(bean.getUserName());
        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.civ_userimg)
        CircleImageView civUserimg;
        @InjectView(R.id.tv_username)
        TextView tvUsername;
        @InjectView(R.id.tv_time)
        TextView tvTime;
        @InjectView(R.id.tv_content)
        TextView tvContent;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
            view.setTag(this);
        }
    }
}
