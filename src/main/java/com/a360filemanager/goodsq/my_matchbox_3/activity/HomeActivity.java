package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/12.
 */
public class HomeActivity extends BaseActvity {
    @InjectView(R.id.home_iv_friend)
    ImageView homeIvFriend;
    @InjectView(R.id.home_iv_topic)
    ImageView homeIvTopic;
    @InjectView(R.id.home_iv_message)
    ImageView homeIvMessage;
    @InjectView(R.id.home_iv_find)
    ImageView homeIvFind;
    @InjectView(R.id.home_iv_person)
    ImageView homeIvPerson;
    @InjectView(R.id.home_ll_tag)
    LinearLayout homeLlTag;
    @InjectView(R.id.home_fl_content)
    FrameLayout homeFlContent;

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void init() {
        homeLlTag.getChildAt(0).setSelected(true);
        showFragment(0);
    }

    @OnClick({R.id.home_iv_friend, R.id.home_iv_topic, R.id.home_iv_message, R.id.home_iv_find, R.id.home_iv_person})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_iv_friend:
                showFragment(0);
                break;
            case R.id.home_iv_topic:
                showFragment(1);
                break;
            case R.id.home_iv_message:
                showFragment(2);
                break;
            case R.id.home_iv_find:
                showFragment(3);
                break;
            case R.id.home_iv_person:
                showFragment(4);
                break;
        }
        //int tag = Integer.parseInt(view.getTag().toString());
        //showFragment(tag);
    }

    Fragment[] fragments = new Fragment[5];
    //记录上一次的选项卡的索引
    int currIndex = -1;

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
