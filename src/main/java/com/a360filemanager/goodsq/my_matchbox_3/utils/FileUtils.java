package com.a360filemanager.goodsq.my_matchbox_3.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/6.
 */
public class FileUtils {

    public static final List<File> getAllFiles(String dir) {
        File file = new File(dir);
        if (file.exists() && file.isDirectory()) {
            List<File> list = new ArrayList<>();
            traverseFile(file, list);
            return list;
        } else {
            return null;
        }
    }

    private static final void traverseFile(File dir, List<File> list) {
        File[] files = dir.listFiles();
        if (files == null)
            return;
        else {
            for (File file : files) {
                if (file.isDirectory())
                    traverseFile(file, list);
                else
                    list.add(file);
            }
        }
    }

    public static final List<File> getAllPicture(File dir) {
        return getAllPicture(dir.getAbsolutePath());
    }

    public static final List<File> getAllPicture(String dir) {
        List<File> pics = new ArrayList<>();
        List<File> allFiles = getAllFiles(dir);
        for (File file : allFiles) {
            if (file.getName().endsWith(".jpg") || file.getName().endsWith(".png")) {
                pics.add(file);
            }
        }
        return pics;
    }




        /*============================================================================================*/



    /**
     * 按时间排序的所有图片list
     */
    private static ArrayList<SingleImageModel> allImages = new ArrayList<>();
    public static ArrayList<SingleImageModel> getAllImages() {
        return allImages;
    }
    /**
     * 从手机中获取所有的手机图片
     */
    public static void startGetImageThread(final Context mContext){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;//数据库中有个MediaStore.Images.Media.EXTERNAL_CONTENT_URI字段可以得到图片文件的上层目录
                ContentResolver contentResolver = mContext.getContentResolver();
                //获取jpeg和png格式的文件，并且按照时间进行倒序
                Cursor cursor = contentResolver.query(uri, null, MediaStore.Images.Media.MIME_TYPE + "=\"image/jpeg\" or " +
                        MediaStore.Images.Media.MIME_TYPE + "=\"image/png\"", null, MediaStore.Images.Media.DATE_MODIFIED+" desc");
                if (cursor != null){
                    allImages.clear();
                    while (cursor.moveToNext()){
                        SingleImageModel singleImageModel = new SingleImageModel();
                        singleImageModel.path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));//获取图片路径
                        try {
                            singleImageModel.date = Long.parseLong(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATE_MODIFIED)));//获取图片日期
                        }catch (NumberFormatException e){
                            singleImageModel.date  = System.currentTimeMillis();
                        }
                        try {
                            singleImageModel.id = Long.parseLong(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)));//获取图片在数据库中的ID
                        }catch (NumberFormatException e){
                            singleImageModel.id = 0;
                        }
                        allImages.add(singleImageModel);
                    }
                Intent intent = new Intent("notify ImageAdapter data set changed");
                mContext.sendBroadcast(intent);
                }
            }
        }).start();
    }
}
