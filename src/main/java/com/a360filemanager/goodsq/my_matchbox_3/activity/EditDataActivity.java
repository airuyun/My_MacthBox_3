package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.utils.MyOnCheckedChangeListener;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by goodsq on 2016/8/11.
 */
public class EditDataActivity extends BaseActvity {
    @InjectView(R.id.edit_data_iv_goBack)
    ImageView editDataIvGoBack;
    @InjectView(R.id.rl)
    RelativeLayout rl;
    @InjectView(R.id.edit_data_figure)
    CircleImageView editDataFigure;
    @InjectView(R.id.edit_data_et_nickName)
    EditText editDataEtNickName;
    @InjectView(R.id.edit_data_iv_clearNickName)
    ImageView editDataIvClearNickName;
    @InjectView(R.id.edit_data_tv_entry)
    TextView editDataTvEntry;

    @Override
    public int getLayout() {
        return R.layout.activity_edit_data;
    }

    @Override
    public void init() {
        MyOnCheckedChangeListener.instance(editDataEtNickName);
    }

    @OnClick({R.id.edit_data_iv_goBack, R.id.edit_data_figure, R.id.edit_data_et_nickName, R.id.edit_data_iv_clearNickName, R.id.edit_data_tv_entry})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_data_iv_goBack:
                break;
            case R.id.edit_data_figure:
                startActivity(new Intent(this,SelectTopicActivity.class));
                break;
            case R.id.edit_data_et_nickName:
                break;
            case R.id.edit_data_iv_clearNickName:
                break;
            case R.id.edit_data_tv_entry:
                startActivity(new Intent(this,HomeActivity.class));
                break;
        }
    }
}
