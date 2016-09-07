package com.a360filemanager.goodsq.my_matchbox_3.activity;

import android.view.View;
import android.widget.TextView;

import com.a360filemanager.goodsq.my_matchbox_3.MyApp;
import com.a360filemanager.goodsq.my_matchbox_3.R;
import com.a360filemanager.goodsq.my_matchbox_3.base.BaseActvity;
import com.a360filemanager.goodsq.my_matchbox_3.bean.HotTopicBean;
import com.a360filemanager.goodsq.my_matchbox_3.httpUtils.HotTopicHttpUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.AccessServerUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.CallbackUtils;
import com.a360filemanager.goodsq.my_matchbox_3.utils.ConcernUtils;
import com.a360filemanager.goodsq.my_matchbox_3.view.TagCloudDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import me.next.tagview.TagCloudView;

/**
 * Created by goodsq on 2016/8/11.
 */
public class SelectTopicActivity extends BaseActvity {

    @InjectView(R.id.tag_cloud_view)
    TagCloudView tagCloundView;

    List<String> tags = new ArrayList<>();
    List<HotTopicBean.ListBean> mList;
    List<HotTopicBean.ListBean> result = new ArrayList<>();

    @Override
    public int getLayout() {
        return R.layout.activity_select_topic;
    }

    @Override
    public void init() {

        getAllHotTopicList();
    }

    @OnClick(R.id.selectTopic_tv_finish)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.selectTopic_tv_finish:
                ConcernTopic();
                break;
        }
    }

    /*======================================获取所有的话题========================================*/
    private void getAllHotTopicList() {
        HotTopicHttpUtils.getAllHotTopic(new CallbackUtils<HotTopicBean>() {
            @Override
            public void onSuccess(HotTopicBean hotTopicBean) {
                HotTopicBean bean = hotTopicBean;
                mList = hotTopicBean.getList();
                int len = mList.size();
                for (int i = 0; i < len; i++) {//获取所有热门标签
                    tags.add(mList.get(i).getName());
                }
                tagCloundView.setTags(tags);//把获取的热门标签显示到标签云上
                tagCloundView.setOnTagClickListener(onTagClickListener);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /*========================================标签点击监听========================================*/
    TagCloudView.OnTagClickListener onTagClickListener = new TagCloudView.OnTagClickListener() {
        @Override
        public void onTagClick(int position) {
            TextView tag = (TextView) tagCloundView.getChildAt(position);
            int i = hasTag(tag.getText().toString());
            if (i != -1) {
                result.remove(i);
                tag.setSelected(false);//去掉点击效果
                tag.setTextColor(getResources().getColor(R.color.colorAccent));
            } else {//没有，添加
                result.add(mList.get(position));
                tag.setSelected(true);
                tag.setTextColor(getResources().getColor(R.color.colorArmyGreen));
            }
        }
    };

    private int hasTag(String str) {
        for (int i = 0; i < result.size(); i++) {
            if (str.equals(result.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    /*======================================关注选择的话题========================================*/
    private void ConcernTopic() {
        if (result.size() >= 3) {
            ConcernUtils concernUtils = new ConcernUtils(this);
            for (int i = 0; i < result.size(); i++) {
                HotTopicHttpUtils.concernedHotTopic(result.get(i).getTopicId());
                concernUtils.addAttention(result.get(i).getTopicId());//用于保存已经关注的话题，保存关注话题的id
            }
            finish();
        } else {
            TagCloudDialog dialog = new TagCloudDialog(this);
            dialog.show();
        }
    }
}
