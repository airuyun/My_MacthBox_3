package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.bean.HotTopicBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/29.
 */
public class MoreTopicListActivity extends BaseActvity {
    @InjectView(R.id.more_topic_list_tv_topic_classify)
    TextView moreTopicListTvTopicClassify;
    @InjectView(R.id.more_topic_list_lv)
    ListView moreTopicListLv;

    List<String> mList = new ArrayList<>();


    @Override
    public int getLayout() {
        return R.layout.activity_more_topic_list;
    }

    @Override
    public void init() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        HotTopicBean bean = (HotTopicBean) intent.getSerializableExtra("bean");
        for (int i = 0; i < bean.getList().size(); i++) {
            mList.add(bean.getList().get(i).getName());
        }
        moreTopicListTvTopicClassify.setText(title);
        moreTopicListLv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mList));

    }

    @OnClick(R.id.more_topic_list_iv_cancel)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more_topic_list_iv_cancel:
                finish();
                break;
        }
    }
}
