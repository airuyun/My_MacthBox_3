package com.a360filemanager.goodsq.my_matchbox_3.utils;

import org.apache.http.client.HttpClient;

/**
 * Created by goodsq on 2016/8/10.
 */
public interface CallbackUtils<T> {

    public void onSuccess(T t);
    public void onFinish();
}
