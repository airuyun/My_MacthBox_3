package com.a360filemanager.goodsq.my_matchbox_3.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/15.
 */
public class GoodUserBean {

    /**
     * List : [{"userId":6,"userImg":"/head/032bd178-9c1d-49bd-b161-e9dbedda8713.jpg","userName":"VENTI"},{"userId":40,"userImg":"/head/e6e6bf0a-9c71-4b3b-9b06-388811399117.jpg","userName":"qq"},{"userId":20,"userImg":"/head/53daf900-1db2-4915-9bb4-a25e518650c2.jpg","userName":"O_0"}]
     * result : 0
     */

    private String result;
    /**
     * userId : 6
     * userImg : /head/032bd178-9c1d-49bd-b161-e9dbedda8713.jpg
     * userName : VENTI
     */

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
        private int userId;
        private String userImg;
        private String userName;

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
