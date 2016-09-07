package com.a360filemanager.goodsq.my_matchbox_3.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/8/11.
 */
public class TimeUtils {

    //判断 时间
    public static final String formatTime(String time) {
        //2016-08-10 21:17:32
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long longTime = format.parse(time).getTime();
            //判断与当前时间的差距
            long currTime = System.currentTimeMillis() - longTime;
            //1分钟之内
            if (currTime < 60 * 1000) {
                //一分钟之内
                return currTime / 1000 + "秒之前";
            } else if (currTime < 60 * 1000 * 60) {
                return currTime / 1000 / 60 + "分钟之前";
            } else if (currTime < 60 * 1000 * 60 * 24) {
                //一天之内
                return currTime / 1000 / 60 / 60 + "小时前";
            } else if (currTime / 1000 / 60 / 60 / 24 < 365) {
                return currTime / 1000 / 60 / 60 / 24 + "天之前";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "很久很久之前...";
    }

}
