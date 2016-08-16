package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.bean.UserLoginInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.ServerUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.MyOnCheckedChangeListener;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by goodsq on 2016/8/11.
 */
public class EditDataActivity extends BaseActvity {
    @InjectView(R.id.edit_data_figure)
    CircleImageView editDataFigure;
    @InjectView(R.id.edit_data_et_nickName)
    EditText editDataEtNickName;

    @Override
    public int getLayout() {
        return R.layout.activity_edit_data;
    }

    @Override
    public void init() {
        editDataEtNickName.setOnEditorActionListener(listener);//回车监听
    }

    @OnClick({R.id.edit_data_iv_goBack, R.id.edit_data_figure, R.id.edit_data_iv_clearNickName, R.id.edit_data_tv_entry})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_data_iv_goBack:
                finish();//退出当前页
                break;
            case R.id.edit_data_figure://设置头像
                startActivity(new Intent(this, setFigureActivity.class));
                break;
            case R.id.edit_data_iv_clearNickName:
                editDataEtNickName.setText("");
                break;
            case R.id.edit_data_tv_entry:
                setNickName();
                break;
        }
    }

    private void setNickName() {
        MyApp.getInstance().getUser().setUsername(editDataEtNickName.getText().toString());//获取昵称并保存
        startActivity(new Intent(this, HomeActivity.class));
    }

    TextView.OnEditorActionListener listener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            setNickName();
            return false;
        }
    };

}
