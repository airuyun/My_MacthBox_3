package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.HotTopicHttpUtils;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.PostHttpUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by goodsq on 2016/8/29.
 */
public class EditPostActivity extends BaseActvity {
    @InjectView(R.id.edit_post_tv_publish)
    TextView editPostTvPublish;
    @InjectView(R.id.edit_post_et_edit_post)
    EditText editPostEtEditPost;
    @InjectView(R.id.edit_post_iv_add_img)
    ImageView editPostIvAddImg;

    private static final int REQUEST_CODE = 0;
    private static final int DEFAULT_VALUE = 0;
    String path;//图片路径
    int topicId;
    String msg;

    @Override
    public int getLayout() {
        return R.layout.activity_edit_post;
    }

    @Override
    public void init() {
        editPostEtEditPost.addTextChangedListener(watcher);
        topicId = getIntent().getIntExtra("topicId",DEFAULT_VALUE);
    }

    @OnClick({R.id.edit_post_tv_cancel, R.id.edit_post_tv_publish, R.id.edit_post_et_edit_post, R.id.edit_post_iv_add_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_post_tv_cancel:
                finish();
                break;
            case R.id.edit_post_tv_publish:
                Log.e("TAG","---------------------1230"+path+"  =======   "+topicId+"======="+msg);
                PostHttpUtils.getPublishPost(this,path,topicId,msg);//发布帖子
                finish();
                break;
            case R.id.edit_post_iv_add_img:
                startActivityForResult(new Intent(this,SetPortraitActivity.class),REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_CANCELED){
            path = data.getStringExtra("path");
            editPostIvAddImg.setImageBitmap(BitmapFactory.decodeFile(path));
        }
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            msg = charSequence.toString();
            if (charSequence.length()>0) {
                editPostTvPublish.setSelected(true);
                editPostTvPublish.setClickable(true);
            }
            else {
                editPostTvPublish.setSelected(false);
                editPostTvPublish.setClickable(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
