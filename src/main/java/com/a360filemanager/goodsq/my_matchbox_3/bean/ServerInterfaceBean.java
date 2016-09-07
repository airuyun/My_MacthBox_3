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
    public static final String UserId = "userId";//用户Id接口
    public static final String Update = "userupdateUserInfo";//用户更新接口
    public static final String HOT_TOPIC = "usergetTopicListHot";//热门话题接口
    public static final String CONCERN_HOT_TOPIC = "useraddFocusTopic";//关注热门话题接口
    public static final String AttentionFriend = "useraddFocus";//关注好友接口
    public static final String FriendPost = "usergetMyFriendPost";//获取好友的帖子接口
    public static final String GetGoodList = "usergetTopUrlByFriendId";//获取帖子的点赞列表
    public static final String GetAllComment = "usergetDiscussByFriendId";//获取所有评论
    public static final String iLike = "useraddTopForFriend";//点赞和取消赞
    public static final String iNoLike = "usercancleTop";
    public static final String MyAttentionTopic = "usergetMyFocusTopic";//获取已关注的所有话题，没有用
    public static final String MyAttentionPost = "usergetMyActionTopIcByUserId";//获取已关注话题的所有帖子
    public static final String PublishComment = "useraddDiscuss";//发布评论

    public static final String PublishPost = "userpostFriendCircle";//发布帖子
    public static final String NewTopicList = "usergetTopicListNew";//新话题
    public static final String UpdateTopicList = "usergetTopicListHasNew";//有更新的话题
    public static final String CreateTopic = "usersaveTopic";//创建新话题
    public static final String GetAttention = "usergetMyAction";//获取我的好友
    public static final String GetFans = "usergetMyFans";//获取我的粉丝
    public static final String GetMostNewTopic = "usergetIsNewFriendCircle";//获取所有话题的最新帖子
    public static final String GetMostHotTopic = "usergetFriendCircleIsHot";//获取所有话题的最热帖子
    public static final String GetRecommendTopic = "usergetTopicListNew";//通过userid 获取最新话题推荐
    public static final String GetPostOFTopic = "usergetHotOrNewFriendCircles";//获取话题中的帖子



    //获取用户信息
    public static final String UserInfo = "usergetUserInfoById";

    public static final String DEFAULT_PASSWORD = "40413451116";//默认登陆密码

    public static final String getImageUrl(String url) {//获取头像
        return BASE_URL + url;
    }

    //跟换服务器后 改变地址
    public static final String getImageNewUrl(String oldurl) {
        return oldurl.replace("123.184.33.74", "118.192.147.148");
    }
}
