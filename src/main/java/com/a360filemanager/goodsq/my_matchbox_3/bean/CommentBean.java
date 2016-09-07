package com.a360filemanager.goodsq.my_matchbox_3.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/15.
 */
public class CommentBean {


    /**
     * List : [{"content":"123123123123123123123","createDate":"2016-08-15 10:51:45","dicussId":24,"userId":576,"userImg":"/head/6a7c3caf-1059-45cb-a32e-bf0fd247a2a7.jpg","userName":" ❤ 合奏ゞ情绪、"},{"content":"412412341234","createDate":"2016-08-15 10:54:15","dicussId":25,"userId":576,"userImg":"/head/6a7c3caf-1059-45cb-a32e-bf0fd247a2a7.jpg","userName":" ❤ 合奏ゞ情绪、"}]
     * result : 0
     */

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

    public static class ListBean {
        private String content;
        private String createDate;
        private int dicussId;
        private int userId;
        private String userImg;
        private String userName;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getDicussId() {
            return dicussId;
        }

        public void setDicussId(int dicussId) {
            this.dicussId = dicussId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
