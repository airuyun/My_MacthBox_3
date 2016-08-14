package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/11.
 */
public class SelectTopicActivity extends BaseActvity {
    @InjectView(R.id.selectTopic_tv_finish)
    TextView selectTopicTvFinish;

    @Override
    public int getLayout() {
        return R.layout.activity_select_topic;
    }

    @Override
    public void init() {

    }

    @OnClick(R.id.selectTopic_tv_finish)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.selectTopic_tv_finish:
                startActivity(new Intent(this,HomeActivity.class));
                break;
        }
    }
}
