package com.a360filemanager.goodsq.my_matchbox_3.bean;

import java.util.List;

/**
 * Created by goodsq on 2016/8/29.
 */
public class RecommendTopicBean {
    /**
     * list : [{"name":"lz就因为离线请留言","seeCount":0,"topicId":78},{"name":"哈哈哈哈哈哈","seeCount":0,"topicId":77},{"name":"我就就是话题","seeCount":13,"topicId":76},{"name":"施主，你不是来化缘的","seeCount":18,"topicId":75},{"name":"fdgfdg","seeCount":4,"topicId":74},{"name":"约定时间","seeCount":14,"topicId":73},{"name":"经典广告","seeCount":20,"topicId":72},{"name":"有故事的人","seeCount":7777,"topicId":71},{"name":"说好的永不分离那","seeCount":16571,"topicId":70},{"name":"This is a Topic","seeCount":64,"topicId":69},{"name":"测试透传指令","seeCount":51081,"topicId":68},{"name":"Tr","seeCount":15446,"topicId":67},{"name":"SSSS","seeCount":26,"topicId":66},{"name":"天空龙","seeCount":23,"topicId":65},{"name":"????3??¡è?","seeCount":499,"topicId":64},{"name":"ä¸\u009cæ³\u0095å¤«","seeCount":10,"topicId":63},{"name":"铁路局","seeCount":17082,"topicId":62},{"name":"铁路局条","seeCount":0,"topicId":61},{"name":"有问题","seeCount":3,"topicId":60},{"name":"什么鬼什么鬼","seeCount":3,"topicId":59}]
     * message : 获取成功
     * result : 0
     */

    private String message;
    private String result;
    /**
     * name : lz就因为离线请留言
     * seeCount : 0
     * topicId : 78
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
