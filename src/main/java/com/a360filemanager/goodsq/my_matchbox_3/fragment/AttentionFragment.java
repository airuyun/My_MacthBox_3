package com.a360filemanager.goodsq.my_matchbox_3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.activity.ContactsSearchActivity;
import com.a360filemanager.goodsq.my_matchbox_3.adapter.AttentionAdapter;
import com.a360filemanager.goodsq.my_matchbox_3.addresslist.IndexBar;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseFragment;
import com.a360filemanager.goodsq.my_matchbox_3.bean.FriendBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.GetAllFriendsHttpUtils;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.MyAttentionTopicHttpUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;
import com.a360filemanager.goodsq.my_matchbox_3.view.AttentionDialog;
import com.a360filemanager.goodsq.my_matchbox_3.view.ScrollViewWithListView;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/24.
 */
public class AttentionFragment extends BaseFragment implements IndexBar.OnIndexBarClickListener {

    @InjectView(R.id.fans_lv)
    ScrollViewWithListView fansLv;
/*    @InjectView(R.id.attention_indexBar)
    IndexBar attentionIndexBar;*/

    AttentionAdapter adapter;
    List<FriendBean.FirendsBean> mList;

    @Override
    public View getLayout() {
        return View.inflate(getContext(), R.layout.fragment_fans, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doRequest();
    }

    @OnClick({R.id.fans_rl_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fans_rl_search:
                startActivity(new Intent(getContext(), ContactsSearchActivity.class));
                break;
        }
    }

    private void doRequest() {
        GetAllFriendsHttpUtils.getAllFriends(new CallbackUtils<FriendBean>() {

            @Override
            public void onSuccess(FriendBean friendBean) {
                mList = friendBean.getFirends();
                adapter = new AttentionAdapter(getContext(), mList);
                fansLv.setAdapter(adapter);
                fansLv.setOnItemClickListener(itemClickListener);
                //attentionIndexBar.setLetter(friendBean.getFirends());
            }

            @Override
            public void onFinish() {

            }
        });

    }

    @Override
    public void onIndexBarClick(int position, String letter) {
        //传进去的首字母
        if (letter.equals("#"))
            fansLv.setSelection(0);
        else
            fansLv.setSelection(adapter.getFirstIndexFromAdapter(letter));
    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.e("TAG","-----------55555----"+i+mList.get(i).getUserName());
            MyApp.getInstance().setFriendId(mList.get(i).getUserId());
            MyApp.getInstance().setFriendNickName(mList.get(i).getUserName());
            MyApp.getInstance().setFriendHead(mList.get(i).getUrl());
            AttentionDialog dialog = new AttentionDialog(getContext());
            dialog.show();
        }
    };

}
