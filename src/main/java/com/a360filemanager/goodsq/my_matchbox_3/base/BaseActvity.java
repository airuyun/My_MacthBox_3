package com.a360filemanager.goodsq.my_matchbox_3.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by goodsq on 2016/8/8.
 */
public abstract class BaseActvity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.inject(this);
        init();
    }

    public abstract int getLayout();
    public abstract void init();

    public void showToast(final String str){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActvity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
