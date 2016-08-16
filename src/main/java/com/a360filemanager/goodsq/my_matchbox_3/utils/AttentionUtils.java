package com.a360filemanager.goodsq.my_matchbox_3.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/8/10.
 */
public class AttentionUtils {
    HashSet<Integer> set;
    SPHelper helper;

    public AttentionUtils(Context context) {
        helper = new SPHelper("TopicId", context); // 1,3,5,7;
        //key, topic    value  =  1,3,4,5,7;
        set = new HashSet<>();
        String str = helper.getStringt("topic");
        if (str != null) {
            String[] array = str.split(",");
            for (int i = 0; i < array.length; i++) {
                set.add(Integer.parseInt(array[i]));
            }
        }
    }

    public List<Integer> getAllAttention() {
        List<Integer> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }


    //追加 新话题，
    public void addAttention(int id) {
        set.add(id);
        save();
    }

    private void save() {
        Iterator<Integer> it = set.iterator();
        String result = "";
        while (it.hasNext()) {
            Integer id = it.next();
            result += id + ",";
        }
        result = result.substring(0, result.length() - 1);
        helper.putValue(new SPHelper.Key_Value("topic", result));
    }

    //取消 话题
    public void removeAttenetion(int id) {
        try {
            set.remove(id);
        } catch (Exception e) {//error
        }
    }


}
