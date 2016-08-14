package com.a360filemanager.goodsq.my_matchbox_3;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by goodsq on 2016/8/11.
 */

//当EditText输入改变时执行
public class MyTextWatcher implements TextWatcher {

    EditText editText;
    ImageView imageView;
    TextView textView;
    int tag;

    public MyTextWatcher(EditText editText, ImageView imageView) {
        this.editText = editText;
        this.imageView = imageView;
    }

    public MyTextWatcher(EditText editText, ImageView imageView, int tag) {
        this.editText = editText;
        this.imageView = imageView;
        this.tag = tag;
    }

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
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        boolean a = false,b = false;
        Log.e("TAG", "-----99988------"+tag);
        if (charSequence.length() > 0) {
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.INVISIBLE);
        }
        if (tag == 0){
            if (editText.getText().toString().length()>0){
                a = true;
            }else {
                a = false;
            }
        }else if (tag == 1) {
            if (editText.getText().toString().length()>0){
                b=true;
            }else {
                b = false;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(editText.getText());
            if (sb.length() == 4 | sb.length() == 9) {
                sb.insert(sb.length() - 1, ' ');
                editText.setText("");//先清空
                editText.setText(sb.toString());
                editText.setSelection(sb.length());//将光标移动到index处
                sb.delete(0, sb.length());
            }
        }else if (tag == 2) {
            Log.e("TAG", "-----999111------");
            if (editText.getText().toString().length() >= 4) {
                Log.e("TAG", "-----999------"+editText.getText().toString().length());
                textView.setEnabled(true);
            } else {
                Log.e("TAG", "-----999------");
                textView.setEnabled(false);
            }
        }
        if (a){

        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
