package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.bean.PostBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.PostHttpUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/12.
 */
public class HomeActivity extends BaseActvity {
    @InjectView(R.id.home_ll_tag)
    LinearLayout homeLlTag;

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void init() {
        showFragment(0);
        PostHttpUtils.getAllConcernPost(new CallbackUtils<PostBean>() {//关注的帖子
            @Override
            public void onSuccess(PostBean postBean) {
                if (postBean.getList().size() == 0) {
                    startActivity(new Intent(HomeActivity.this, SelectTopicActivity.class));
                }
            }

            @Override
            public void onFinish() {
            }
        });
    }

    @OnClick({R.id.home_iv_friend, R.id.home_iv_topic, R.id.home_iv_message, R.id.home_iv_find, R.id.home_iv_person})
    public void onClick(View view) {
        int tag = Integer.parseInt(view.getTag().toString());
        showFragment(tag);
    }

    Fragment[] fragments = new Fragment[5];
    int currIndex = -1;//记录上一次的选项卡的索引

    private void showFragment(int index) {
        if (currIndex == index) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (currIndex != -1) {
            ft.detach(fragments[currIndex]);//销毁当前Fragment视图
            homeLlTag.getChildAt(currIndex).setSelected(false);
        }
        if (fragments[index] != null) {
            ft.attach(fragments[index]);//显示Fragment视图
        } else {
            createFragment(index);
            ft.replace(R.id.home_fl_content, fragments[index]);
        }
        homeLlTag.getChildAt(index).setSelected(true);
        currIndex = index;
        ft.commit();//提交、刷新
    }

    public String[] fragmentTag = {"FriendFragment", "TopicFragment", "MessageFragment", "FindFragment", "PersonFragment"};

    private void createFragment(int index) {
        try {
            Fragment fragment = (Fragment) Class.forName("com.a360filemanager.goodsq.my_matchbox_3.fragment." + fragmentTag[index]).newInstance();
            fragments[index] = fragment;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
