package com.a360filemanager.goodsq.my_matchbox_3.bean;

/**
 * Created by goodsq on 2016/8/10.
 */
public class UserRegisterInfoBean {


    /**
     * message : 注册成功
     * result : 0
     * userId : 514  服务器分配的ID
     */

    private String message;
    private String result;
    private int userId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public  void setResult(String result) {
        this.result = result;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
