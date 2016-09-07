package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/24.
 */
public class ContactsSearchActivity extends BaseActvity {
    @InjectView(R.id.contacts_search_iv_return)
    ImageView contactsSearchIvReturn;
    @InjectView(R.id.contacts_search_tv_search)
    TextView contactsSearchTvSearch;

    @Override
    public int getLayout() {
        return R.layout.activity_contacts_search;
    }

    @Override
    public void init() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick({R.id.contacts_search_iv_return, R.id.contacts_search_tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contacts_search_iv_return:
                finish();
                break;
            case R.id.contacts_search_tv_search:
                break;
        }
    }
}
