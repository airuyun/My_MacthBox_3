package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a360filemanager.goodsq.my_matchbox_3.MyTextWatcher;
import com.a360filemanager.goodsq.my_matchbox_3.utils.AccessServerUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.ConstantUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.MyOnCheckedChangeListener;
import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/11.
 */
public class LoginActivity extends BaseActvity {
    @InjectView(R.id.login_et_phoneNum)
    EditText loginEtPhoneNum;
    @InjectView(R.id.login_iv_clearPhoneNum)
    ImageView loginIvClearPhoneNum;
    @InjectView(R.id.login_et_password)
    EditText loginEtPassword;
    @InjectView(R.id.register_cb_showPassword)
    CheckBox registerCbShowPassword;
    @InjectView(R.id.login_iv_clearPassword)
    ImageView loginIvClearPassword;
    @InjectView(R.id.login_tv_loginButton)
    TextView loginTvLoginButton;

    Fragment fragment;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        loginTvLoginButton.setEnabled(false);
        loginEtPhoneNum.requestFocus();//让 EditText获得光标;
        fragment = getSupportFragmentManager().findFragmentById(R.id.thirdparty);
        loginEtPhoneNum.addTextChangedListener(new MyTextWatcher(loginEtPhoneNum, loginIvClearPhoneNum, loginTvLoginButton, ConstantUtils.LOGIN_PHONENUM_TAG));
        loginEtPassword.addTextChangedListener(new MyTextWatcher(loginEtPassword, loginIvClearPassword, loginTvLoginButton, ConstantUtils.LOGIN_PASSWORD_TAG));
        registerCbShowPassword.setOnCheckedChangeListener(MyOnCheckedChangeListener.instance(loginEtPassword));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.login_tv_loginButton, R.id.login_iv_goBack, R.id.login_tv_jumpRegister, R.id.login_iv_clearPhoneNum, R.id.login_iv_clearPassword, R.id.register_cb_showPassword, R.id.login_iv_down})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_tv_loginButton:
                if (loginEtPhoneNum.getText().toString().replace(" ", "").matches("[1][34578][\\d]{9}") && loginEtPassword.getText().toString().length() >= 6) {
                    //登录服务器
                    AccessServerUtils mAccessServerUtils = new AccessServerUtils(this, loginEtPhoneNum.getText().toString().replace(" ", ""), loginEtPassword.getText().toString(), "Phone");
                    mAccessServerUtils.loginServer();
                } else if (loginEtPhoneNum.getText().toString().replace(" ","").length() != 11 || !loginEtPhoneNum.getText().toString().replace(" ", "").matches("[1][34578][\\d]{9}")) {
                    Toast.makeText(LoginActivity.this, "请输入正确的电话号码", Toast.LENGTH_SHORT).show();
                } else if (loginEtPassword.getText().toString().length() < 6) {
                    Toast.makeText(LoginActivity.this, "密码不能小于6位", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码有误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_iv_goBack:
                finish();
                break;
            case R.id.login_tv_jumpRegister:
                startActivity(new Intent(this, RegisterActiviry.class));
                break;
            case R.id.login_iv_clearPhoneNum:
                loginEtPhoneNum.setText("");
                break;
            case R.id.login_iv_clearPassword:
                loginEtPassword.setText("");
                break;
            case R.id.login_iv_down://选择地区
                break;
        }
    }
}
