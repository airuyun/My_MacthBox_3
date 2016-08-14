package com.a360filemanager.goodsq.my_matchbox_3.utils;


import android.text.InputType;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Created by goodsq on 2016/8/11.
 */
public class MyOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

    static EditText editText;

    private static MyOnCheckedChangeListener singleInstance;
    private MyOnCheckedChangeListener(){};
    public static MyOnCheckedChangeListener instance(EditText et) {
        editText = et;
        if (singleInstance == null) {
            singleInstance = new MyOnCheckedChangeListener();
        }
        return singleInstance;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        //切换眼睛
        if (b) {
            Log.e("TAG", "---------11111-------" + b);
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            Log.e("TAG", "---------22222-------" + b);
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }
}
