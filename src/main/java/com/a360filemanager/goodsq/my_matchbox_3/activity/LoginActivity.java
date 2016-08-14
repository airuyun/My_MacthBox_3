package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.utils.MyOnCheckedChangeListener;
import com.a360filemanager.goodsq.my_matchbox_3.MyTextWatcher;
import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.tencent.connect.common.Constants;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/11.
 */
public class LoginActivity extends BaseActvity {
    @InjectView(R.id.login_tv_zone)
    TextView loginTvZone;
    @InjectView(R.id.login_iv_down)
    ImageView loginIvDown;
    @InjectView(R.id.login_iv_areaCode)
    EditText loginIvAreaCode;
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
    @InjectView(R.id.login_tv_forgetPassword)
    TextView loginTvForgetPassword;

    Fragment fragment;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        loginTvLoginButton.setEnabled(false);
        loginEtPhoneNum.requestFocus();//一个布局里面有两个 EditText，光标默认停在第二个EditText上;
        fragment = getSupportFragmentManager().findFragmentById(R.id.thirdparty);
        loginEtPhoneNum.addTextChangedListener(new MyTextWatcher(loginEtPhoneNum, loginIvClearPhoneNum, 1));
        loginEtPassword.addTextChangedListener(new MyTextWatcher(loginEtPassword, loginIvClearPassword,0));
        registerCbShowPassword.setOnCheckedChangeListener(MyOnCheckedChangeListener.instance(loginEtPassword));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_LOGIN) {
            Log.e("TAG", "--qqq---------" + requestCode + "--" + resultCode + "--" + data.toString());
            fragment.onActivityResult(requestCode, resultCode, data);
        } else {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @OnClick({R.id.login_tv_loginButton,R.id.login_iv_goBack, R.id.login_tv_jumpRegister, R.id.login_iv_clearPhoneNum, R.id.login_iv_clearPassword, R.id.register_cb_showPassword,R.id.login_iv_down})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_tv_loginButton:
                break;
            case R.id.login_iv_goBack://返回
                finish();
                break;
            case R.id.login_tv_jumpRegister://跳转到注册页
                startActivity(new Intent(this, RegisterActiviry.class));
                break;
            case R.id.login_iv_clearPhoneNum://清除号码
                loginEtPhoneNum.setText("");
                break;
            case R.id.login_iv_clearPassword://清除密码
                loginEtPassword.setText("");
                break;
            case R.id.login_iv_down://选择地区

                break;
        }
    }
}
