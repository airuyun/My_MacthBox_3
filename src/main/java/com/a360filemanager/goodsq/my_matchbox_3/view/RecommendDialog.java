package com.a360filemanager.goodsq.my_matchbox_3.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.UserInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.AttentionFriendHttpUtils;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.AccessServerHttpUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/8/16.
 */
public class RecommendDialog extends Dialog {

    //给定几个ID
    // 3 4 6


    public RecommendDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    ListView listView;
    TextView finish;

    List<UserInfoBean> mList = new ArrayList<>();

    MyAdpater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_recommend);
        listView = (ListView) findViewById(R.id.dialog_lv);
        finish = (TextView) findViewById(R.id.dialog_tv_finish);
        setCancelable(false);
        adapter = new MyAdpater();
        listView.setAdapter(adapter);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


    @Override
    public void show() {
        super.show();
        AttentionFriendHttpUtils.attentionFriend(3);
        AttentionFriendHttpUtils.attentionFriend(4);
        AttentionFriendHttpUtils.attentionFriend(6);
        AccessServerHttpUtils.getUserInfo(3, new CallbackUtils<UserInfoBean>() {

            @Override
            public void onSuccess(UserInfoBean userInfoBean) {
                mList.add(userInfoBean);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFinish() {

            }
        });
        AccessServerHttpUtils.getUserInfo(4, new CallbackUtils<UserInfoBean>() {

            @Override
            public void onSuccess(UserInfoBean userInfoBean) {
                mList.add(userInfoBean);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFinish() {

            }
        });
        AccessServerHttpUtils.getUserInfo(6, new CallbackUtils<UserInfoBean>() {

            @Override
            public void onSuccess(UserInfoBean userInfoBean) {
                mList.add(userInfoBean);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFinish() {

            }
        });
    }


    class MyAdpater extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
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
            ViewHolder holder;
            if (view == null) {
                view = View.inflate(getContext(), R.layout.item_recommend, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else
                holder = (ViewHolder) view.getTag();
            UserInfoBean user = mList.get(i);
            holder.itemTvUserid.setText("ID:" + user.getUserId());
            holder.itemTvUserinfo.setText(user.getMyInfo());
            holder.itemTvUsername.setText(user.getUsername());
            if (user.getUrl() != null && !user.getUrl().equals(""))
                x.image().bind(holder.itemCivUserimg, ServerInterfaceBean.getImageUrl(user.getUrl()));
            else
                holder.itemCivUserimg.setImageResource(R.mipmap.icon_register_avatar_default);
            return view;
        }

        class ViewHolder {
            @InjectView(R.id.item_civ_userimg)
            CircleImageView itemCivUserimg;
            @InjectView(R.id.item_tv_username)
            TextView itemTvUsername;
            @InjectView(R.id.item_tv_userid)
            TextView itemTvUserid;
            @InjectView(R.id.item_tv_userinfo)
            TextView itemTvUserinfo;

            ViewHolder(View view) {
                ButterKnife.inject(this, view);
            }
        }
    }
}
