package com.a360filemanager.goodsq.my_matchbox_3.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by goodsq on 2016/8/16.
 */
public class HotTopicBean implements Serializable {

    /**
     * list : [{"name":"注意言行","seeCount":321,"topicId":7},{"name":"段子","seeCount":269,"topicId":29},{"name":"颜值时代","seeCount":263,"topicId":2},{"name":"花","seeCount":181,"topicId":31},{"name":"五月","seeCount":107,"topicId":8},{"name":"我们都爱笑","seeCount":61,"topicId":5},{"name":"被人嫌弃是一种怎样的体验","seeCount":39,"topicId":6},{"name":"七夕","seeCount":33,"topicId":32},{"name":"傅园慧","seeCount":23,"topicId":41},{"name":"大方款式大方护航","seeCount":17,"topicId":43},{"name":"115","seeCount":15,"topicId":44},{"name":"注意言行啊","seeCount":11,"topicId":9},{"name":"阿斯顿 ","seeCount":10,"topicId":42},{"name":"呵呵哒哒","seeCount":8,"topicId":10},{"name":"里约奥运会","seeCount":8,"topicId":39},{"name":"Q","seeCount":8,"topicId":30},{"name":"泡泡","seeCount":7,"topicId":12},{"name":"呵呵","seeCount":7,"topicId":3},{"name":"呵呵了","seeCount":4,"topicId":11},{"name":"三哥的传说","seeCount":4,"topicId":35}]
     * message : 获取成功
     * result : 0
     */

    private String message;
    private String result;
    private List<ListBean> list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        public static final String[] titles = {"热门话题", "推荐话题", "有更新的话题"};
        public static final int TYPE_HOT = 0;
        public static final int TYPE_RECOMMEND = 1;
        public static final int TYPE_UPDATE = 2;


        public static final int TYPE_NEW = 3;

        private String name;
        private int topicId;
        private int seeCount;

        private int type = TYPE_HOT;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTopicId() {
            return topicId;
        }

        public void setTopicId(int topicId) {
            this.topicId = topicId;
        }

        public int getSeeCount() {
            return seeCount;
        }

        public void setSeeCount(int seeCount) {
            this.seeCount = seeCount;
        }
    }
}
