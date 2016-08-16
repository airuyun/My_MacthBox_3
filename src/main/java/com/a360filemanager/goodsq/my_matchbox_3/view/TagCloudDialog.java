package com.a360filemanager.goodsq.my_matchbox_3.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.a360filemanager.goodsq.my_matchbox_3.R;

/**
 * Created by goodsq on 2016/8/16.
 */
public class TagCloudDialog extends Dialog {
    public TagCloudDialog(Context context) {
        super(context, R.style.TagCloundDialogTheme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tagdialog_layout);
        findViewById(R.id.dialog_tv_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TagCloudDialog.this.dismiss();
            }
        });
    }
}
