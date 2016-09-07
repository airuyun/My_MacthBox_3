package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.adapter.CommentAdapter;
import com.a360filemanager.goodsq.my_matchbox_3.adapter.GoodListViewAdapter;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.bean.CommentBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.GoodUserBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.PostBean;
import com.a360filemanager.goodsq.my_matchbox_3.bean.ServerInterfaceBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.PostHttpUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;
import com.a360filemanager.goodsq.my_matchbox_3.view.DisplayTextView;
import com.a360filemanager.goodsq.my_matchbox_3.view.EmPopupWindow;
import com.a360filemanager.goodsq.my_matchbox_3.view.GoodListView;
import com.a360filemanager.goodsq.my_matchbox_3.view.ScrollListView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import butterknife.InjectView;
import butterknife.OnClick;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

/**
 * Created by Administrator on 2016/8/12.
 */
public class PostDeatilActivity extends BaseActvity {

    PostBean.ListBean bean;
    @InjectView(R.id.post_iv_share)
    ImageView postIvShare;

    @InjectView(R.id.detail_et_command)
    EditText detailEtCommand;

    @InjectView(R.id.detail_tv_publish)
    TextView detailTvPublish;

    @InjectView(R.id.detail_cb_isTop)
    CheckBox detailCbIsTop;

    @InjectView(R.id.ll)
    LinearLayout ll;
    @InjectView(R.id.post_tv_topicname)
    TextView postTvTopicname;

    @InjectView(R.id.post_civ_head)
    ImageView postCivHead;
    @InjectView(R.id.post_tv_nickname)
    TextView postTvNickname;
    @InjectView(R.id.post_iv_content)
    ImageView postIvContent;
    @InjectView(R.id.post_dtv)
    DisplayTextView postDtv;

    @InjectView(R.id.detail_lv)
    ScrollListView detailLv;

    @InjectView(R.id.detail_glv)
    GoodListView detailGlv;

    @InjectView(R.id.detail_xsv)
    PullToRefreshScrollView detailXsv;

    GoodListViewAdapter goodListViewAdapter;

    CommentAdapter commentAdapter;

    String path;

    @Override
    public void init() {
        //数据
        bean = (PostBean.ListBean) getIntent().getSerializableExtra("bean");
        //请求点赞列表
        PostHttpUtils.getPostGoodList(bean.getFriendId(), getGoodUserListener);
        //请求评论列表
        PostHttpUtils.getAllComment(bean.getFriendId(), getAllCommentListener);
        //判断是否点赞
        detailCbIsTop.setChecked(bean.getIsTop() == 0 ? false : true);
        postTvTopicname.setText(bean.getTopicName());
        x.image().bind(postCivHead, ServerInterfaceBean.getImageUrl(bean.getImgUrl()));
        postTvNickname.setText(bean.getUserName());
        if (bean.getPhotoList() != null && bean.getPhotoList().size() > 0) {
            postIvContent.setVisibility(View.VISIBLE);
            ImageOptions options = new ImageOptions.Builder().setCrop(false).build();
            x.image().bind(postIvContent, ServerInterfaceBean.getImageUrl(bean.getPhotoList().get(0).getUrl()), options);
            new Thread() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(ServerInterfaceBean.getImageUrl(bean.getPhotoList().get(0).getUrl()));
                        InputStream is = url.openStream();
                        Bitmap bmp = BitmapFactory.decodeStream(is);
                        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp.jpg";
                        bmp.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(path));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } else
            postIvContent.setVisibility(View.GONE);
        postDtv.setText(bean.getMsg());

        //设置点赞
        detailCbIsTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                PostHttpUtils.iLike(b, bean.getFriendId(), new CallbackUtils<Boolean>() {

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        PostHttpUtils.getPostGoodList(bean.getFriendId(), getGoodUserListener);

                    }

                    @Override
                    public void onFinish() {

                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, getIntent().putExtra("isTop", detailCbIsTop.isChecked()));
        finish();
    }

    CallbackUtils<CommentBean> getAllCommentListener = new CallbackUtils<CommentBean>() {

        @Override
        public void onSuccess(CommentBean commentBean) {
            commentAdapter = new CommentAdapter(PostDeatilActivity.this, commentBean.getList());
            detailLv.setAdapter(commentAdapter);

        }

        @Override
        public void onFinish() {

        }
    };

    CallbackUtils<GoodUserBean> getGoodUserListener = new CallbackUtils<GoodUserBean>() {

        @Override
        public void onSuccess(GoodUserBean goodUserBean) {
            goodListViewAdapter = new GoodListViewAdapter(PostDeatilActivity.this, goodUserBean.getList());
            detailGlv.setAdapter(goodListViewAdapter);
        }

        @Override
        public void onFinish() {

        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_post_detail;
    }


    @OnClick({R.id.post_tv_addmo, R.id.detail_tv_publish, R.id.post_iv_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.post_tv_addmo:
                showEmGridView();
                break;
            case R.id.detail_tv_publish:
                String content = detailEtCommand.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    PostHttpUtils.publishPostComment(content, bean.getFriendId(), new CallbackUtils<Boolean>() {

                        @Override
                        public void onSuccess(Boolean aBoolean) {
                            showToast(aBoolean ? "发布成功" : "发布失败");
                            detailEtCommand.setText("");
                            if (aBoolean)
                                PostHttpUtils.getAllComment(bean.getFriendId(), getAllCommentListener);

                        }

                        @Override
                        public void onFinish() {
                        }
                    });
                } else {
                    showToast("请输入评论内容!!!");
                }
                break;
            case R.id.post_iv_share:
                //分享
                //showShare();
                break;
        }
    }

/*    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(bean.getTopicName());
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText(bean.getMsg());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        LogUtils.e(path);
        oks.setImagePath(path);//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        //oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }*/

    private void showEmGridView() {
        EmPopupWindow window = new EmPopupWindow(this);
        window.showAsDropDown(detailEtCommand);
        window.setCallback(new CallbackUtils<String>() {

            @Override
            public void onSuccess(String s) {
                Editable editable = detailEtCommand.getEditableText();
                int index = detailEtCommand.getSelectionStart();
                editable.insert(index, s);
                String name = s.replace("【", "").replace("】", "");
                ImageSpan span = null;
                try {
                    span = new ImageSpan(PostDeatilActivity.this, R.drawable.class.getField(name).getInt(null));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                editable.setSpan(span, index, index + s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
