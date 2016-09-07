package com.a360filemanager.goodsq.my_matchbox_3.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/10.
 */
public class PostBean {


    private String result;

    private java.util.List<ListBean> List;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ListBean> getList() {
        return List;
    }

    public void setList(List<ListBean> List) {
        this.List = List;
    }

    public static class ListBean implements Serializable {
        private int isAction; //是否点击
        private int type;     //类型
        private String msg;   //文字
        private int friendId; //帖子id
        private int topCount; //点赞数
        private int shareCount;//分享数
        private String imgUrl;///用户头像
        private int isCollection; //是否收藏
        private int userId;      //用户id
        private int topicId;    //话题id
        private String userName; //
        private int isTop;       //是否点赞
        private String createDate; //创建日期
        private String topicName;  //话题名称
        private int discussCount;  //访问数量

        private java.util.List<PhotoListBean> photoList;

        public int getIsAction() {
            return isAction;
        }

        public void setIsAction(int isAction) {
            this.isAction = isAction;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getFriendId() {
            return friendId;
        }

        public void setFriendId(int friendId) {
            this.friendId = friendId;
        }

        public int getTopCount() {
            return topCount;
        }

        public void setTopCount(int topCount) {
            this.topCount = topCount;
        }

        public int getShareCount() {
            return shareCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getIsCollection() {
            return isCollection;
        }

        public void setIsCollection(int isCollection) {
            this.isCollection = isCollection;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getTopicId() {
            return topicId;
        }

        public void setTopicId(int topicId) {
            this.topicId = topicId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getIsTop() {
            return isTop;
        }

        public void setIsTop(int isTop) {
            this.isTop = isTop;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public int getDiscussCount() {
            return discussCount;
        }

        public void setDiscussCount(int discussCount) {
            this.discussCount = discussCount;
        }

        public List<PhotoListBean> getPhotoList() {
            return photoList;
        }

        public void setPhotoList(List<PhotoListBean> photoList) {
            this.photoList = photoList;
        }

        public static class PhotoListBean implements Serializable {
            private String imgUrl;
            private String url;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
