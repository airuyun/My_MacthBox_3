package com.a360filemanager.goodsq.my_matchbox_3.bean;

import java.util.List;

/**
 * Created by goodsq on 2016/8/23.
 */
public class NewTopicBean {
    /**
     * list : [{"name":"星座大全","seeCount":23,"topicId":48},{"name":"12345678998765432100","seeCount":133941,"topicId":47},{"name":"心疼宝强","seeCount":208818,"topicId":46},{"name":"ç\u0082¹èµ\u009e","seeCount":142962,"topicId":45},{"name":"115","seeCount":29707,"topicId":44},{"name":"大方款式大方护航","seeCount":82354,"topicId":43},{"name":"阿斯顿 ","seeCount":6503,"topicId":42},{"name":"傅园慧","seeCount":231,"topicId":41},{"name":"首都机场","seeCount":4,"topicId":40},{"name":"里约奥运会","seeCount":417,"topicId":39},{"name":"那些不能言说的伤","seeCount":17091,"topicId":38},{"name":"211","seeCount":37,"topicId":37},{"name":"Yy","seeCount":3,"topicId":36},{"name":"三哥的传说","seeCount":4,"topicId":35},{"name":"小岳岳","seeCount":3,"topicId":34},{"name":"离歌","seeCount":1,"topicId":33},{"name":"七夕","seeCount":14267,"topicId":32},{"name":"花","seeCount":15130,"topicId":31},{"name":"Q","seeCount":8,"topicId":30},{"name":"段子","seeCount":11903,"topicId":29}]
     * message : 获取成功
     * result : 0
     */

    private String message;
    private String result;
    /**
     * name : 星座大全
     * seeCount : 23
     * topicId : 48
     */

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

    public static class ListBean {
        private String name;
        private int seeCount;
        private int topicId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSeeCount() {
            return seeCount;
        }

        public void setSeeCount(int seeCount) {
            this.seeCount = seeCount;
        }

        public int getTopicId() {
            return topicId;
        }

        public void setTopicId(int topicId) {
            this.topicId = topicId;
        }
    }
}
