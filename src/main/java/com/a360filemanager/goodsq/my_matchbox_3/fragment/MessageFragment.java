package com.a360filemanager.goodsq.my_matchbox_3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.activity.ContactsActivity;
import com.a360filemanager.goodsq.my_matchbox_3.adapter.MyFragmentPagerAdapter;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/12.
 */
public class MessageFragment extends BaseFragment {

    @InjectView(R.id.message_tv_add_friends)
    TextView messageTvAddFriends;
    @InjectView(R.id.message_rb_private_letter)
    RadioButton messageRbPrivateLetter;
    @InjectView(R.id.message_rb_remind)
    RadioButton messageRbRemind;
    @InjectView(R.id.message_rg)
    RadioGroup messageRg;
    @InjectView(R.id.message_tv_contacts)
    TextView messageTvContacts;
    @InjectView(R.id.message_vp)
    ViewPager messageVp;

    List<Fragment> fragments = new ArrayList<>();

    @Override
    public View getLayout() {
        return View.inflate(getContext(), R.layout.fragment_message, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragments.add(new privateLetterFragment());
        fragments.add(new remindFragment());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        messageVp.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(),fragments));
        messageVp.setOnPageChangeListener(pageChangeListener);
        messageRbPrivateLetter.setChecked(true);

    }

    @OnClick({R.id.message_tv_add_friends, R.id.message_rb_private_letter, R.id.message_rb_remind, R.id.message_tv_contacts})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.message_tv_add_friends:
                break;
            case R.id.message_rb_private_letter:
                messageVp.setCurrentItem(0);
                break;
            case R.id.message_rb_remind:
                messageVp.setCurrentItem(1);
                break;
            case R.id.message_tv_contacts:
                startActivity(new Intent(getContext(), ContactsActivity.class));
                break;
        }
    }

    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            messageRg.check(position%2 == 0?R.id.message_rb_private_letter:R.id.message_rb_remind );
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
