package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.content.Intent;
import android.icu.text.Replaceable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.bean.FriendBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/25.
 */
public class ChatActivity extends BaseActvity {
    @InjectView(R.id.chat_iv_return)
    ImageView chatIvReturn;
    @InjectView(R.id.chat_tv_nickname)
    TextView chatTvNickname;
    @InjectView(R.id.chat_iv_setting)
    ImageView chatIvSetting;
    @InjectView(R.id.chat_fl_replace)
    FrameLayout chatFlReplace;

    EaseChatFragment baseChatFragment;
    EaseContactListFragment easeContactListFragment;
    EaseConversationListFragment easeConversationListFragment;

    @Override
    public int getLayout() {
        return R.layout.activity_chat;
    }

    @Override
    public void init() {



        //申请账号时 同时向环信注册ID，username 手机号 第三方OPENID
        FriendBean.FirendsBean user = (FriendBean.FirendsBean) getIntent().getSerializableExtra("user");//环信的ID号
        baseChatFragment = new EaseChatFragment();
        Bundle bundle = new Bundle();
        //单聊
        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        //和谁聊天
        bundle.putString(EaseConstant.EXTRA_USER_ID, MyApp.getInstance().getFriendId()+"");
        //设置好友昵称
        bundle.putString("nickName", MyApp.getInstance().getFriendNickName());
        //bundle.putString("nickName", "111");
        //设置好友头像
        if (MyApp.getInstance().getFriendHead() != null && !MyApp.getInstance().getFriendHead().equals(""))
            bundle.putString("otherImg", MyApp.getInstance().getFriendHead());


        //设置自己的头像
        if (!TextUtils.isEmpty(MyApp.getInstance().getUser().getUrl()))
            bundle.putString("userImg", MyApp.getInstance().getUser().getUrl());
        baseChatFragment.setArguments(bundle);
        //聊天好友的昵称
        chatTvNickname.setText(MyApp.getInstance().getFriendNickName());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.chat_fl_replace, baseChatFragment, "chat");
        ft.commit();
    }

    @OnClick({R.id.chat_iv_return, R.id.chat_tv_nickname, R.id.chat_iv_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chat_iv_return:
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.chat_tv_nickname:
                break;
            case R.id.chat_iv_setting:
                break;
        }
    }
}
