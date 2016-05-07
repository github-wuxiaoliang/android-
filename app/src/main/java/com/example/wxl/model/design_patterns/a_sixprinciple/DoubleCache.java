package com.example.wxl.model.design_patterns.a_sixprinciple;

import android.graphics.Bitmap;

/**
 * Created on 2016/5/7.
 *
 * @author wuxiaoliang
 * @since 1.0
 * <p/>
 * 双缓存内
 */
public class DoubleCache {
    private DiskCache mDiskCache = new DiskCache();
    private ImageCache mImageCache = new ImageCache();

    public Bitmap getBitMap(String url) {
        Bitmap bitMap = mImageCache.getBitMap(url);
        if (bitMap == null) {
            bitMap = mDiskCache.getBitMap(url);
        }
        return bitMap;
    }

    public void putBitMap(String url, Bitmap bitmap) {
        mDiskCache.putBitMap(url, bitmap);
        mImageCache.putBitMap(url, bitmap);
    }
}
