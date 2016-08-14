package com.a360filemanager.goodsq.my_matchbox_3.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2016/8/6.
 */
public class ConstanUtils {

    //地址
    public static final String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String IMAGE_PATH = BASE_PATH + "/MatchBox/head";

    static {
        new File(IMAGE_PATH).getParentFile().mkdirs();
    }


}
