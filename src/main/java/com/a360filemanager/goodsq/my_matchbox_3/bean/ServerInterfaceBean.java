package com.a360filemanager.goodsq.my_matchbox_3.bean;

/**
 * Created by goodsq on 2016/8/10.
 *
 * 浏览器上这样访问：http://118.192.147.148:8080/Matchbox/usergetTopicListHot?userId=1
 */
public class ServerInterfaceBean {

    public static final String BASE_URL = "http://118.192.147.148:8080/Matchbox";//服务器域名
    public static final String Login = "useruserlogin";//用户登录接口
    public static final String Register = "userregist";//用户注册接口
    public static final String Update = "userupdateUserInfo";//用户更新接口
    public static final String HOT_TOPIC = "usergetTopicListHot";//热门话题接口
    public static final String CONCERN_HOT_TOPIC = "useraddFocusTopic";//关注热门话题接口


    public static final String DEFAULT_PASSWORD = "40413451116";//默认登陆密码
}
