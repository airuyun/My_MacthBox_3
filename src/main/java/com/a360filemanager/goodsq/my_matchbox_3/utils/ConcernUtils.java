package com.a360filemanager.goodsq.my_matchbox_3.utils;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/8/10.
 */
public class ConcernUtils {//保存已关注的帖子状态
    SPHelper helper;
    HashSet<Integer> set;

    public ConcernUtils(Context context) {
        helper = new SPHelper("TopicId", context);
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


    //追加 新话题，保存话题的Id
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

    //删除 话题
    public void removeAttenetion(int id) {
        try {
            set.remove(id);
        } catch (Exception e) {//error
        }
    }


}
