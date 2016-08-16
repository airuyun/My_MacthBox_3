package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.MyTextWatcher;
import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.utils.BoxUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.ConstantUtils;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by goodsq on 2016/8/13.
 */
public class VerifyActivity extends BaseActvity {
    @InjectView(R.id.verify_et_verificationCode)
    EditText verifyEtVerificationCode;
    @InjectView(R.id.verify_iv_clearVerificationCode)
    ImageView verifyIvClearVerificationCode;
    @InjectView(R.id.verify_tv_submit)
    TextView verifyTvSubmit;
    @InjectView(R.id.verify_tv_receiveVoiceVerification)
    TextView verifyTvReceiveVoiceVerification;

    String areaCode;
    String phoneNum;
    Dialog dialog;
    MyCountDownTimer countDownTimer;

    @Override
    public int getLayout() {
        return R.layout.activity_verify;
    }

    @Override
    public void init() {
        areaCode = getIntent().getStringExtra("areaCode");
        phoneNum = getIntent().getStringExtra("phoneNum");
        verifyTvSubmit.setEnabled(false);
        countDownTimer = new MyCountDownTimer();//倒计时计时器
        countDownTimer.start();
        verifyEtVerificationCode.addTextChangedListener(new MyTextWatcher(verifyEtVerificationCode, verifyIvClearVerificationCode, verifyTvSubmit, ConstantUtils.VERIFY_TAG));
    }

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

    @Override
    protected void onDestroy() {//保存剩余时间
        super.onDestroy();
        if (countDownTimer != null) {
            MyApp.getInstance().remainTime(countDownTimer.remainTime);
        }

    }

    @OnClick({R.id.verify_iv_goBack, R.id.verify_iv_clearVerificationCode, R.id.verify_tv_submit, R.id.verify_tv_receiveVoiceVerification})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.verify_iv_goBack:
                finish();//关闭当前activity
                break;
            case R.id.verify_iv_clearVerificationCode:
                verifyEtVerificationCode.setText("");
                break;
            case R.id.verify_tv_submit:
                verification();
                break;
            case R.id.verify_tv_receiveVoiceVerification:
                SMSSDK.getVoiceVerifyCode(areaCode, phoneNum);
                countDownTimer = new MyCountDownTimer();
                countDownTimer.start();
                break;
        }
    }

    private void verification() {
        dialog = BoxUtils.getProgressDialog(this, "正在验证", "请稍后");//在前，否则将不会被执行
        dialog.show();
        SMSSDK.submitVerificationCode(areaCode, phoneNum, verifyEtVerificationCode.getText().toString());
    }

    EventHandler eventHandler = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            super.afterEvent(event, result, data);
            if (dialog != null && dialog.isShowing()) {//验证码提交成功，关闭dialog
                dialog.dismiss();
            }
            if (result == SMSSDK.RESULT_COMPLETE) {//解析验证结果
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//验证成功
                    startActivity(new Intent(VerifyActivity.this, SetPasswordActivity.class));
                }
            } else {
                showToast("验证码错误");
            }
        }
    };

    /*=======================================倒计时计时器=========================================*/
    private class MyCountDownTimer extends CountDownTimer {
        long remainTime;

        public MyCountDownTimer() {
            super(30000, 1000);
        }

        @Override
        public void onTick(long time) {
            verifyTvReceiveVoiceVerification.setEnabled(false);
            verifyTvReceiveVoiceVerification.setText("已发送:" + time / 1000 + "秒");
            remainTime = time;
        }

        @Override
        public void onFinish() {
            verifyTvReceiveVoiceVerification.setEnabled(true);
            verifyTvReceiveVoiceVerification.setText("接收语音验证码");
            remainTime = 0;
        }
    }
}
