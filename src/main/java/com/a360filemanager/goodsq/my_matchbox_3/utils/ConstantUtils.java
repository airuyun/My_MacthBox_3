package com.a360filemanager.goodsq.my_matchbox_3.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2016/8/6.
 */
public class ConstantUtils {

    //地址
    public static final String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String IMAGE_PATH = BASE_PATH + "/MatchBox/head";
    public static final int REGISTER_PHONENUM_TAG = 0;
    public static final int REGISTER_PASSWORD_TAG = 3;
    public static final int LOGIN_PHONENUM_TAG = 1;
    public static final int LOGIN_PASSWORD_TAG = 2;
    public static final int VERIFY_TAG = 3;
    public static final boolean isThirdParty = false;

    static {
        new File(IMAGE_PATH).getParentFile().mkdirs();
    }


}
