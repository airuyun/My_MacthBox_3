package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.MyTextWatcher;
import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.utils.ConstantUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.MyOnCheckedChangeListener;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/4.
 */
public class SetPasswordActivity extends BaseActvity {
    @InjectView(R.id.password_et)
    EditText passwordEt;
    @InjectView(R.id.password_iv_show)
    CheckBox passwordIvShow;
    @InjectView(R.id.password_iv_clear)
    ImageView passwordIvClear;
    @InjectView(R.id.password_tv_submit)
    TextView passwordTvSubmit;

    @Override
    public void init() {
        passwordEt.addTextChangedListener(new MyTextWatcher(passwordEt, passwordIvClear, passwordTvSubmit, ConstantUtils.REGISTER_PASSWORD_TAG));
        passwordEt.setOnEditorActionListener(listener);
        passwordIvShow.setOnCheckedChangeListener(MyOnCheckedChangeListener.instance(passwordEt));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_set_password;
    }

    @OnClick({R.id.password_iv_clear, R.id.password_tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.password_iv_clear:
                passwordEt.setText("");
                break;
            case R.id.password_tv_submit:
                setPassword();
                break;
        }
    }

    private void setPassword() {
        MyApp.getInstance().getUser().setPassword(passwordEt.getText().toString());//记录输入的密码
        startActivity(new Intent(this, EditDataActivity.class));
    }

    TextView.OnEditorActionListener listener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            setPassword();
            return false;
        }
    };
}
