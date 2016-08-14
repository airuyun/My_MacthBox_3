package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.MyTextWatcher;
import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.utils.BoxUtils;
import com.tencent.connect.common.Constants;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by goodsq on 2016/8/9.
 */
public class RegisterActiviry extends BaseActvity {

    Fragment fragment;
    @InjectView(R.id.register_iv_goBack)
    ImageView registerIvGoBack;
    @InjectView(R.id.register_tv_jumpLogin)
    TextView registerTvJumpLogin;
    @InjectView(R.id.register_et_areaCode)
    EditText registerEtAreaCode;
    @InjectView(R.id.register_et_inputPhoneNum)
    EditText registerEtInputPhoneNum;
    @InjectView(R.id.register_iv_clearPhoneNum)
    ImageView registerIvClearPhoneNum;
    @InjectView(R.id.register_tv_sendVerificationCode)
    TextView registerTvSendVerificationCode;

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void init() {
        //registerTvSendVerificationCode.setEnabled(false);//不能触发点击事件
        //出错地方：应该这样拿到XML中的碎片（fragment)包含的类的实例，而不是new一个ThirdPartyFragment对象
        fragment = getSupportFragmentManager().findFragmentById(R.id.thirdparty);
        registerEtInputPhoneNum.requestFocus();//一个布局里面有两个 EditText，光标默认停在第二个EditText上;
        registerEtInputPhoneNum.addTextChangedListener(new MyTextWatcher(registerEtInputPhoneNum, registerIvClearPhoneNum,registerTvSendVerificationCode , 1));
        registerEtInputPhoneNum.setOnEditorActionListener(editorAction);//EditText回车监听
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


    @OnClick({R.id.register_iv_goBack, R.id.register_tv_jumpLogin, R.id.register_tv_sendVerificationCode, R.id.register_iv_clearPhoneNum})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_iv_goBack:
                finish();//退出当前Activity
                break;
            case R.id.register_tv_jumpLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.register_tv_sendVerificationCode:
                Intent intent = new Intent(RegisterActiviry.this, VerifyActivity.class);
                intent.putExtra("areaCode", registerEtAreaCode.getText().toString());
                intent.putExtra("phoneNum", registerEtInputPhoneNum.getText().toString().replace(" ",""));
                startActivity(intent);
                //sendVerificationCode();
                break;
            case R.id.register_iv_clearPhoneNum:
                registerEtInputPhoneNum.setText("");
                break;
        }
    }

    Dialog dialog;

    private void sendVerificationCode() {
        if (MyApp.getInstance().noSendVerificationCode()){
            showToast("同一手机号30秒内只能提交1次，请稍后再试");
            return;
        }
        if (registerEtInputPhoneNum.getText().toString().replace(" ", "").matches("[1][34578][\\d]{9}")) {
            Log.e("TAG",registerEtInputPhoneNum.getText().toString().replace(" ", ""));
            //发送验证码
            dialog = BoxUtils.getProgressDialog(this, "正在发送验证码", "请稍后......");
            SMSSDK.getVerificationCode(registerEtAreaCode.getText().toString(), registerEtInputPhoneNum.getText().toString().replace(" ", ""));
        } else {
            Toast.makeText(RegisterActiviry.this, "请输入正确的电话号码", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 注册回调
     */

    @Override
    protected void onResume() {
        super.onResume();
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    EventHandler eventHandler = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {//当电话号码发送成功时的回调
            super.afterEvent(event, result, data);
            //关闭dialog
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            // 解析注册结果
            if (result == SMSSDK.RESULT_COMPLETE) {
                //验证码发送成功
                if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    showToast("验证码发送成功");
                    Intent intent = new Intent(RegisterActiviry.this, VerifyActivity.class);
                    intent.putExtra("areaCode", registerEtAreaCode.getText().toString());
                    intent.putExtra("phoneNum", registerEtInputPhoneNum.getText().toString().replace(" ",""));
                    startActivity(intent);
                }
            } else {
                showToast("验证码发送失败");
            }
        }
    };

    TextView.OnEditorActionListener editorAction = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            sendVerificationCode();
            return false;//当ruture true是，一次回车将执行onEditorAction（）方法两次
        }
    };

}
