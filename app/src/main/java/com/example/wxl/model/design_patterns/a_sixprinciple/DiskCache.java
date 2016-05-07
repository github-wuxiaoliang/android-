package com.example.wxl.model.design_patterns.a_sixprinciple;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created on 2016/5/7.
 *
 * @author wuxiaoliang
 * @since 1.0
 *
 * 磁盘缓存内
 */
public class DiskCache {
    private String mCacheDir = "sdcard/cache/";

    public Bitmap getBitMap(String url) {
        return BitmapFactory.decodeFile(mCacheDir + url);
    }

    public void putBitMap(String url, Bitmap bitmap) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mCacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
