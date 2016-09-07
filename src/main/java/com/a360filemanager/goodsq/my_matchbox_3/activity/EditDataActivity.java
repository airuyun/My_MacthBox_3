package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.bean.UserInfoBean;
import com.a360filemanager.goodsq.my_matchbox_3.utils.AccessServerUtils;

import butterknife.InjectView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by goodsq on 2016/8/11.
 */
public class EditDataActivity extends BaseActvity {
    private static final int REQUEST_CODE = 0;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        editDataFigure.setImageBitmap(BitmapFactory.decodeFile(data.getStringExtra("path")));
        MyApp.getInstance().getUser().setUrl(data.getStringExtra("path"));
        UserInfoBean.getInstance().setLocalImage(data.getStringExtra("path"));
    }

    @OnClick({R.id.edit_data_iv_goBack, R.id.edit_data_figure, R.id.edit_data_iv_clearNickName, R.id.edit_data_tv_entry})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_data_iv_goBack:
                finish();
                break;
            case R.id.edit_data_figure://设置头像
                startActivityForResult(new Intent(this, SetPortraitActivity.class),REQUEST_CODE);
                break;
            case R.id.edit_data_iv_clearNickName:
                editDataEtNickName.setText("");
                break;
            case R.id.edit_data_tv_entry:
                setNickName();
                break;
        }
    }

    TextView.OnEditorActionListener listener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            setNickName();
            return false;
        }
    };

    private void setNickName() {
        MyApp.getInstance().getUser().setNickName(editDataEtNickName.getText().toString());//获取昵称并保存
        AccessServerUtils accessServerUtils = new AccessServerUtils(this,MyApp.getInstance().getUser().getUsername(),MyApp.getInstance().getUser().getPassword(),"Phone");
        accessServerUtils.registerServer();
    }

}
