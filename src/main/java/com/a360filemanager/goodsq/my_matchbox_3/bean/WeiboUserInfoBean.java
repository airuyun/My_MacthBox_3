package com.a360filemanager.goodsq.my_matchbox_3.bean;

import java.util.List;

/**
 * Created by goodsq on 2016/8/10.
 */
public class WeiboUserInfoBean {

    /**
     * allow_all_act_msg : false
     * allow_all_comment : true
     * avatar_hd : http://tva4.sinaimg.cn/default/images/default_avatar_male_180.gif
     * avatar_large : http://tva4.sinaimg.cn/default/images/default_avatar_male_180.gif
     * bi_followers_count : 0
     * block_app : 0
     * block_word : 0
     * city : 3
     * class : 1
     * created_at : Fri Nov 16 12:03:34 +0800 2012
     * credit_score : 80
     * description :
     * domain :
     * favourites_count : 0
     * follow_me : false
     * followers_count : 3
     * following : false
     * friends_count : 2
     * gender : m
     * geo_enabled : true
     * id : 3116155395
     * idstr : 3116155395
     * lang : zh-cn
     * location : 广西 桂林
     * mbrank : 0
     * mbtype : 0
     * name : 1419643508_133ce6
     * online_status : 0
     * pagefriends_count : 0
     * profile_image_url : http://tva4.sinaimg.cn/default/images/default_avatar_male_50.gif
     * profile_url : u/3116155395
     * province : 45
     * ptype : 0
     * remark :
     * screen_name : 1419643508_133ce6
     * star : 0
     * status : {"annotations":[{"appid":53,"id":"2816364","name":"2014年广西大学学生创新创业优秀成果展示会投票","title":"2014年广西大学学生创新创业优秀成果展示会投票","url":"http://vote.weibo.com/vid=2816364"}],"attitudes_count":0,"biz_feature":0,"comments_count":0,"created_at":"Sat Nov 08 18:50:04 +0800 2014","darwin_tags":[],"favorited":false,"gif_ids":"","hasActionTypeCard":0,"hot_weibo_tags":[],"id":3774707259777120,"idstr":"3774707259777120","in_reply_to_screen_name":"","in_reply_to_status_id":"","in_reply_to_user_id":"","isLongText":false,"mid":"3774707259777120","mlevel":0,"pic_urls":[],"positive_recom_flag":0,"reposts_count":0,"source_allowclick":0,"source_type":1,"text":"我参与了@共青团广西大学 发起的投票【2014年广西大学学生创新创业优秀成果展示会投票】，我投给了\u201cA0602\u201d这个选项。你也快来表态吧：http://t.cn/R786w9o","text_tag_tips":[],"truncated":false,"userType":0,"visible":{"list_id":0,"type":0}}
     * statuses_count : 4
     * urank : 0
     * url :
     * user_ability : 0
     * verified : false
     * verified_reason :
     * verified_reason_url :
     * verified_source :
     * verified_source_url :
     * verified_trade :
     * verified_type : -1
     * weihao :
     */

    private boolean allow_all_act_msg;
    private boolean allow_all_comment;
    private String avatar_hd;
    private String avatar_large;
    private int bi_followers_count;
    private int block_app;
    private int block_word;
    private String city;
    private int classX;
    private String created_at;
    private int credit_score;
    private String description;
    private String domain;
    private int favourites_count;
    private boolean follow_me;
    private int followers_count;
    private boolean following;
    private int friends_count;
    private String gender;
    private boolean geo_enabled;
    private long id;
    private String idstr;
    private String lang;
    private String location;
    private int mbrank;
    private int mbtype;
    private String name;
    private int online_status;
    private int pagefriends_count;
    private String profile_image_url;
    private String profile_url;
    private String province;
    private int ptype;
    private String remark;
    private String screen_name;
    private int star;
    /**
     * annotations : [{"appid":53,"id":"2816364","name":"2014年广西大学学生创新创业优秀成果展示会投票","title":"2014年广西大学学生创新创业优秀成果展示会投票","url":"http://vote.weibo.com/vid=2816364"}]
     * attitudes_count : 0
     * biz_feature : 0
     * comments_count : 0
     * created_at : Sat Nov 08 18:50:04 +0800 2014
     * darwin_tags : []
     * favorited : false
     * gif_ids :
     * hasActionTypeCard : 0
     * hot_weibo_tags : []
     * id : 3774707259777120
     * idstr : 3774707259777120
     * in_reply_to_screen_name :
     * in_reply_to_status_id :
     * in_reply_to_user_id :
     * isLongText : false
     * mid : 3774707259777120
     * mlevel : 0
     * pic_urls : []
     * positive_recom_flag : 0
     * reposts_count : 0
     * source_allowclick : 0
     * source_type : 1
     * text : 我参与了@共青团广西大学 发起的投票【2014年广西大学学生创新创业优秀成果展示会投票】，我投给了“A0602”这个选项。你也快来表态吧：http://t.cn/R786w9o
     * text_tag_tips : []
     * truncated : false
     * userType : 0
     * visible : {"list_id":0,"type":0}
     */

    private StatusBean status;
    private int statuses_count;
    private int urank;
    private String url;
    private int user_ability;
    private boolean verified;
    private String verified_reason;
    private String verified_reason_url;
    private String verified_source;
    private String verified_source_url;
    private String verified_trade;
    private int verified_type;
    private String weihao;

    public boolean isAllow_all_act_msg() {
        return allow_all_act_msg;
    }

    public void setAllow_all_act_msg(boolean allow_all_act_msg) {
        this.allow_all_act_msg = allow_all_act_msg;
    }

    public boolean isAllow_all_comment() {
        return allow_all_comment;
    }

    public void setAllow_all_comment(boolean allow_all_comment) {
        this.allow_all_comment = allow_all_comment;
    }

    public String getAvatar_hd() {
        return avatar_hd;
    }

    public void setAvatar_hd(String avatar_hd) {
        this.avatar_hd = avatar_hd;
    }

    public String getAvatar_large() {
        return avatar_large;
    }

    public void setAvatar_large(String avatar_large) {
        this.avatar_large = avatar_large;
    }

    public int getBi_followers_count() {
        return bi_followers_count;
    }

    public void setBi_followers_count(int bi_followers_count) {
        this.bi_followers_count = bi_followers_count;
    }

    public int getBlock_app() {
        return block_app;
    }

    public void setBlock_app(int block_app) {
        this.block_app = block_app;
    }

    public int getBlock_word() {
        return block_word;
    }

    public void setBlock_word(int block_word) {
        this.block_word = block_word;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getClassX() {
        return classX;
    }

    public void setClassX(int classX) {
        this.classX = classX;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getCredit_score() {
        return credit_score;
    }

    public void setCredit_score(int credit_score) {
        this.credit_score = credit_score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getFavourites_count() {
        return favourites_count;
    }

    public void setFavourites_count(int favourites_count) {
        this.favourites_count = favourites_count;
    }

    public boolean isFollow_me() {
        return follow_me;
    }

    public void setFollow_me(boolean follow_me) {
        this.follow_me = follow_me;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public int getFriends_count() {
        return friends_count;
    }

    public void setFriends_count(int friends_count) {
        this.friends_count = friends_count;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isGeo_enabled() {
        return geo_enabled;
    }

    public void setGeo_enabled(boolean geo_enabled) {
        this.geo_enabled = geo_enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMbrank() {
        return mbrank;
    }

    public void setMbrank(int mbrank) {
        this.mbrank = mbrank;
    }

    public int getMbtype() {
        return mbtype;
    }

    public void setMbtype(int mbtype) {
        this.mbtype = mbtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOnline_status() {
        return online_status;
    }

    public void setOnline_status(int online_status) {
        this.online_status = online_status;
    }

    public int getPagefriends_count() {
        return pagefriends_count;
    }

    public void setPagefriends_count(int pagefriends_count) {
        this.pagefriends_count = pagefriends_count;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getPtype() {
        return ptype;
    }

    public void setPtype(int ptype) {
        this.ptype = ptype;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public int getStatuses_count() {
        return statuses_count;
    }

    public void setStatuses_count(int statuses_count) {
        this.statuses_count = statuses_count;
    }

    public int getUrank() {
        return urank;
    }

    public void setUrank(int urank) {
        this.urank = urank;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUser_ability() {
        return user_ability;
    }

    public void setUser_ability(int user_ability) {
        this.user_ability = user_ability;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getVerified_reason() {
        return verified_reason;
    }

    public void setVerified_reason(String verified_reason) {
        this.verified_reason = verified_reason;
    }

    public String getVerified_reason_url() {
        return verified_reason_url;
    }

    public void setVerified_reason_url(String verified_reason_url) {
        this.verified_reason_url = verified_reason_url;
    }

    public String getVerified_source() {
        return verified_source;
    }

    public void setVerified_source(String verified_source) {
        this.verified_source = verified_source;
    }

    public String getVerified_source_url() {
        return verified_source_url;
    }

    public void setVerified_source_url(String verified_source_url) {
        this.verified_source_url = verified_source_url;
    }

    public String getVerified_trade() {
        return verified_trade;
    }

    public void setVerified_trade(String verified_trade) {
        this.verified_trade = verified_trade;
    }

    public int getVerified_type() {
        return verified_type;
    }

    public void setVerified_type(int verified_type) {
        this.verified_type = verified_type;
    }

    public String getWeihao() {
        return weihao;
    }

    public void setWeihao(String weihao) {
        this.weihao = weihao;
    }

    public static class StatusBean {
        private int attitudes_count;
        private int biz_feature;
        private int comments_count;
        private String created_at;
        private boolean favorited;
        private String gif_ids;
        private int hasActionTypeCard;
        private long id;
        private String idstr;
        private String in_reply_to_screen_name;
        private String in_reply_to_status_id;
        private String in_reply_to_user_id;
        private boolean isLongText;
        private String mid;
        private int mlevel;
        private int positive_recom_flag;
        private int reposts_count;
        private int source_allowclick;
        private int source_type;
        private String text;
        private boolean truncated;
        private int userType;
        /**
         * list_id : 0
         * type : 0
         */

        private VisibleBean visible;
        /**
         * appid : 53
         * id : 2816364
         * name : 2014年广西大学学生创新创业优秀成果展示会投票
         * title : 2014年广西大学学生创新创业优秀成果展示会投票
         * url : http://vote.weibo.com/vid=2816364
         */

        private List<AnnotationsBean> annotations;
        private List<?> darwin_tags;
        private List<?> hot_weibo_tags;
        private List<?> pic_urls;
        private List<?> text_tag_tips;

        public int getAttitudes_count() {
            return attitudes_count;
        }

        public void setAttitudes_count(int attitudes_count) {
            this.attitudes_count = attitudes_count;
        }

        public int getBiz_feature() {
            return biz_feature;
        }

        public void setBiz_feature(int biz_feature) {
            this.biz_feature = biz_feature;
        }

        public int getComments_count() {
            return comments_count;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public boolean isFavorited() {
            return favorited;
        }

        public void setFavorited(boolean favorited) {
            this.favorited = favorited;
        }

        public String getGif_ids() {
            return gif_ids;
        }

        public void setGif_ids(String gif_ids) {
            this.gif_ids = gif_ids;
        }

        public int getHasActionTypeCard() {
            return hasActionTypeCard;
        }

        public void setHasActionTypeCard(int hasActionTypeCard) {
            this.hasActionTypeCard = hasActionTypeCard;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getIdstr() {
            return idstr;
        }

        public void setIdstr(String idstr) {
            this.idstr = idstr;
        }

        public String getIn_reply_to_screen_name() {
            return in_reply_to_screen_name;
        }

        public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
            this.in_reply_to_screen_name = in_reply_to_screen_name;
        }

        public String getIn_reply_to_status_id() {
            return in_reply_to_status_id;
        }

        public void setIn_reply_to_status_id(String in_reply_to_status_id) {
            this.in_reply_to_status_id = in_reply_to_status_id;
        }

        public String getIn_reply_to_user_id() {
            return in_reply_to_user_id;
        }

        public void setIn_reply_to_user_id(String in_reply_to_user_id) {
            this.in_reply_to_user_id = in_reply_to_user_id;
        }

        public boolean isIsLongText() {
            return isLongText;
        }

        public void setIsLongText(boolean isLongText) {
            this.isLongText = isLongText;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public int getMlevel() {
            return mlevel;
        }

        public void setMlevel(int mlevel) {
            this.mlevel = mlevel;
        }

        public int getPositive_recom_flag() {
            return positive_recom_flag;
        }

        public void setPositive_recom_flag(int positive_recom_flag) {
            this.positive_recom_flag = positive_recom_flag;
        }

        public int getReposts_count() {
            return reposts_count;
        }

        public void setReposts_count(int reposts_count) {
            this.reposts_count = reposts_count;
        }

        public int getSource_allowclick() {
            return source_allowclick;
        }

        public void setSource_allowclick(int source_allowclick) {
            this.source_allowclick = source_allowclick;
        }

        public int getSource_type() {
            return source_type;
        }

        public void setSource_type(int source_type) {
            this.source_type = source_type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isTruncated() {
            return truncated;
        }

        public void setTruncated(boolean truncated) {
            this.truncated = truncated;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public VisibleBean getVisible() {
            return visible;
        }

        public void setVisible(VisibleBean visible) {
            this.visible = visible;
        }

        public List<AnnotationsBean> getAnnotations() {
            return annotations;
        }

        public void setAnnotations(List<AnnotationsBean> annotations) {
            this.annotations = annotations;
        }

        public List<?> getDarwin_tags() {
            return darwin_tags;
        }

        public void setDarwin_tags(List<?> darwin_tags) {
            this.darwin_tags = darwin_tags;
        }

        public List<?> getHot_weibo_tags() {
            return hot_weibo_tags;
        }

        public void setHot_weibo_tags(List<?> hot_weibo_tags) {
            this.hot_weibo_tags = hot_weibo_tags;
        }

        public List<?> getPic_urls() {
            return pic_urls;
        }

        public void setPic_urls(List<?> pic_urls) {
            this.pic_urls = pic_urls;
        }

        public List<?> getText_tag_tips() {
            return text_tag_tips;
        }

        public void setText_tag_tips(List<?> text_tag_tips) {
            this.text_tag_tips = text_tag_tips;
        }

        public static class VisibleBean {
            private int list_id;
            private int type;

            public int getList_id() {
                return list_id;
            }

            public void setList_id(int list_id) {
                this.list_id = list_id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class AnnotationsBean {
            private int appid;
            private String id;
            private String name;
            private String title;
            private String url;

            public int getAppid() {
                return appid;
            }

            public void setAppid(int appid) {
                this.appid = appid;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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
