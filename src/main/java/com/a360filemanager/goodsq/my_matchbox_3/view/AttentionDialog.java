package com.a360filemanager.goodsq.my_matchbox_3.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.activity.ChatActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/25.
 */
public class AttentionDialog extends Dialog {
    @InjectView(R.id.attention_dialog_circle_img)
    CircleImageView attentionDialogCircleImg;
    @InjectView(R.id.attention_dialog_dismiss)
    ImageView attentionDialogDismiss;
    @InjectView(R.id.attention_dialog_nickname)
    TextView attentionDialogNickname;
    @InjectView(R.id.attention_dialog_id)
    TextView attentionDialogId;
    @InjectView(R.id.attention_dialog_person_follow)
    ImageView attentionDialogPersonFollow;
    @InjectView(R.id.attention_dialog_private_letter)
    TextView attentionDialogPrivateLetter;
    @InjectView(R.id.attention_dialog_home)
    TextView attentionDialogHome;

    public AttentionDialog(Context context) {
        super(context, R.style.AttentionDialogTheme);
    }

    public AttentionDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attention_dialog);
        ButterKnife.inject(this);
    }


    @OnClick({R.id.attention_dialog_circle_img, R.id.attention_dialog_dismiss, R.id.attention_dialog_nickname, R.id.attention_dialog_id, R.id.attention_dialog_person_follow, R.id.attention_dialog_private_letter, R.id.attention_dialog_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.attention_dialog_circle_img:
                break;
            case R.id.attention_dialog_dismiss:
                this.dismiss();
                break;
            case R.id.attention_dialog_nickname:
                break;
            case R.id.attention_dialog_id:
                break;
            case R.id.attention_dialog_person_follow:
                break;
            case R.id.attention_dialog_private_letter:
                this.dismiss();
                getContext().startActivity(new Intent(getContext(), ChatActivity.class));
                break;
            case R.id.attention_dialog_home:
                break;
        }
    }


}
