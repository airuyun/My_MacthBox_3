package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.app.Dialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.HotTopicHttpUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.BoxUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/18.
 */
public class CreateTopicActivity extends BaseActvity implements TextWatcher {
    @InjectView(R.id.create_topic_et_name)
    EditText createTopicEtName;
    @InjectView(R.id.create_topic_tv_sign)
    EditText createTopicTvSign;
    @InjectView(R.id.create_topic_tv_size)
    TextView createTopicTvSize;
    @InjectView(R.id.create_topic_btn_commit)
    TextView createTopicBtnCommit;

    @Override
    public void init() {
        createTopicTvSign.addTextChangedListener(this);
        createTopicEtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0 && createTopicTvSign.getText().toString().length() > 0)
                    createTopicBtnCommit.setEnabled(true);
                else
                    createTopicBtnCommit.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_create_topic;
    }


    @OnClick(R.id.create_topic_btn_commit)
    public void onClick() {
        final Dialog dialog = BoxUtils.getProgressDialog(this, "创建中", "正在创建，请稍后...");
        String topicName = createTopicEtName.getText().toString();
        HotTopicHttpUtils.createNewTopic(topicName, new CallbackUtils<Boolean>() {

            @Override
            public void onSuccess(Boolean aBoolean) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                if (aBoolean) {
                    showToast("创建成功");
                    finish();
                } else
                    showToast("创建失败");

            }

            @Override
            public void onFinish() {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        createTopicTvSize.setText(charSequence.length() + "/100");
        if (charSequence.length() > 0 && createTopicEtName.getText().toString().length() > 0)
            createTopicBtnCommit.setEnabled(true);
        else
            createTopicBtnCommit.setEnabled(false);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
