package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.adapter.MyFragmentPagerAdapter;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.fragment.AttentionFragment;
import com.a360filemanager.goodsq.my_matchbox_3.fragment.FansFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/24.
 */
public class ContactsActivity extends BaseActvity {

    @InjectView(R.id.contacts_iv_return)
    ImageView contactsIvReturn;
    @InjectView(R.id.contacts_rb_attention)
    RadioButton contactsRbAttention;
    @InjectView(R.id.contacts_rb_fans)
    RadioButton contactsRbFans;
    @InjectView(R.id.contacts_rg)
    RadioGroup contactsRg;
    @InjectView(R.id.contacts_vp)
    ViewPager contactsVp;

    List<Fragment> mList = new ArrayList<>();

    @Override
    public int getLayout() {
        return R.layout.activity_contacts;
    }

    @Override
    public void init() {
        contactsRbAttention.setChecked(true);
        mList.add(new AttentionFragment());
        mList.add(new FansFragment());
        contactsVp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), mList));
        contactsVp.setOnPageChangeListener(pageChangeListener);

    }

    @OnClick({R.id.contacts_iv_return, R.id.contacts_rb_attention, R.id.contacts_rb_fans, R.id.contacts_rg, R.id.contacts_vp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contacts_iv_return:
                finish();
                break;
            case R.id.contacts_rb_attention:
                contactsVp.setCurrentItem(0);
                break;
            case R.id.contacts_rb_fans:
                contactsVp.setCurrentItem(1);
                break;
            case R.id.contacts_rg:
                break;
            case R.id.contacts_vp:
                break;
        }
    }

    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            contactsRg.check(position % 2 == 0 ? R.id.contacts_rb_attention : R.id.contacts_rb_fans);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
