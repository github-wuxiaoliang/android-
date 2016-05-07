package com.example.wxl.model.design_patterns.a_sixprinciple;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created on 2016/5/7. 内存缓存类
 *
 * @author wuxiaoliang
 * @since 1.0
 */
public class ImageCache {
    private LruCache<String, Bitmap> mLruCache;

    public ImageCache() {
        initImageCache();
    }

    private void initImageCache() {
        //可使用的最大内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory() / 1024;
        //取 1/4
        int cacheSize = maxMemory / 4;
        mLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    public void putBitMap(String url, Bitmap bitmap) {
        mLruCache.put(url, bitmap);
    }

    public Bitmap getBitMap(String url) {
        return mLruCache.get(url);
    }
}
