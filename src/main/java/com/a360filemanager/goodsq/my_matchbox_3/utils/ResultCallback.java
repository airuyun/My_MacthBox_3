package com.a360filemanager.goodsq.my_matchbox_3.utils;

import android.util.Log;

import org.xutils.common.Callback;

import java.util.List;

/**
 * Created by goodsq on 2016/8/10.
 */
//可以不在这里实现Callback.CommonCallback,Callback.CommonCallback一定要指定String类型,否则将不能成功访问服务器
public abstract class ResultCallback implements Callback.CommonCallback<String> {
    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        Log.e("TAG","-----onError-------"+ex.toString());
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }
}
