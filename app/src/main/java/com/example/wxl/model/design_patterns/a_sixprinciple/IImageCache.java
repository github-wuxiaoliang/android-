package com.example.wxl.model.design_patterns.a_sixprinciple;

import android.graphics.Bitmap;

/**
 * Created on 2016/5/7.
 *
 * @author wuxiaoliang
 * @since 1.0
 */
public interface IImageCache {
    Bitmap getBitMap(String url);
    void putBitMap(String url, Bitmap bitmap);
}
