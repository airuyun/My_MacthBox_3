package com.a360filemanager.goodsq.my_matchbox_3;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.utils.ConstantUtils;

/**
 * Created by goodsq on 2016/8/11.
 */

//当EditText输入改变时执行
public class MyTextWatcher implements TextWatcher {

    EditText editText;
    ImageView imageView;
    TextView textView;
    int tag;

    public MyTextWatcher(EditText editText, ImageView imageView, TextView textView, int tag) {
        this.editText = editText;
        this.imageView = imageView;
        this.textView = textView;
        this.tag = tag;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        if (charSequence.length() > 0) {
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.INVISIBLE);
        }
        if (tag == ConstantUtils.REGISTER_PHONENUM_TAG | tag == ConstantUtils.LOGIN_PHONENUM_TAG) {

            if (charSequence.length() > 0) {
                MyApp.getInstance().inputAccount = true;
            } else {
                MyApp.getInstance().inputAccount = false;
            }
/*----------------------------------------电话号码处理--------------------------------------------*/
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < charSequence.length(); i++) {
                if (i != 3 && i != 8 && charSequence.charAt(i) == ' ') {
                    continue;
                } else {
                    sb.append(charSequence.charAt(i));
                    if ((sb.length() == 4 || sb.length() == 9) && sb.charAt(sb.length() - 1) != ' ') {
                        sb.insert(sb.length() - 1, ' ');
                    }
                }
            }
            if (!sb.toString().equals(charSequence.toString())) {
                int index = start + 1;
                if (sb.charAt(start) == ' ') {
                    if (before == 0) {
                        index++;
                    } else {
                        index--;
                    }
                } else {
                    if (before == 1) {
                        index--;
                    }
                }
                editText.setText(sb.toString());
                editText.setSelection(index);
            }
/*------------------------------------------------------------------------------------------------*/
        } else if (tag == ConstantUtils.LOGIN_PASSWORD_TAG) {//登录页密码输入处理

            if (charSequence.length() > 0) {
                MyApp.getInstance().inputPassword = true;
            } else {
                MyApp.getInstance().inputPassword = false;
            }
        } else if (tag == ConstantUtils.VERIFY_TAG) {//验证码输入处理
            if (editText.getText().toString().length() >= 4) {
                textView.setEnabled(true);
            } else {
                textView.setEnabled(false);
            }
        } else if (tag == ConstantUtils.REGISTER_PASSWORD_TAG) {
            if (editText.getText().toString().length() >= 6) {
                textView.setEnabled(true);
            } else {
                textView.setEnabled(false);
            }
        }
        if (tag == ConstantUtils.LOGIN_PHONENUM_TAG | tag == ConstantUtils.LOGIN_PASSWORD_TAG) {
            if (MyApp.getInstance().inputPassword & MyApp.getInstance().inputAccount) {
                textView.setEnabled(true);
            } else {
                textView.setEnabled(false);
            }
        }
        if (tag == ConstantUtils.REGISTER_PHONENUM_TAG) {
            if (editText.getText().toString().replace(" ", "").length() == 11) {
                //当输入完11位电话号码时，隐藏软键盘
                InputMethodManager inputMethodManager = (InputMethodManager) MyApp.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                textView.setEnabled(true);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
