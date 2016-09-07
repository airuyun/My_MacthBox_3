package com.a360filemanager.goodsq.my_matchbox_3.bean;

/**
 * Created by goodsq on 2016/8/10.
 */
public class UserInfoBean {
    /*===========================================单例=============================================*/
    private static UserInfoBean instance;

    private UserInfoBean() {
    }

    public static UserInfoBean getInstance() {
        if (instance == null) {
            instance = new UserInfoBean();
        }
        return instance;
    }
/*================================================================================================*/
    /**
     * message : 登陆成功
     * myInfo :
     * result : 0
     * sex :
     * url :
     * userId : 514  //服务分配的ID
     * username :      //电话号码、openId
     */

    private String message;
    private String myInfo;
    private String result;
    private String sex;
    private String url;
    private long userId;        //服务器自动分配的ID
    private String username;  //登录名，电话号码，QQ登录时，username是openId，注册的使用用到的账号
    private String nickName;//昵称，用户名

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMyInfo() {
        return myInfo;
    }

    public void setMyInfo(String myInfo) {
        this.myInfo = myInfo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /*================================================================================================*/
    private String localImage;
    private String password;
    private String myActionCount;
    private String fansCount;

    public String getLocalImage() {
        return localImage;
    }

    public void setLocalImage(String localImage) {
        this.localImage = localImage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMyActionCount() {
        return myActionCount;
    }

    public void setMyActionCount(String myActionCount) {
        this.myActionCount = myActionCount;
    }

    public String getFansCount() {
        return fansCount;
    }

    public void setFansCount(String fansCount) {
        this.fansCount = fansCount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
