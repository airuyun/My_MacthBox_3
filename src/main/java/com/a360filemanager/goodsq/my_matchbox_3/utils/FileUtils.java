package com.a360filemanager.goodsq.my_matchbox_3.utils;

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
}
