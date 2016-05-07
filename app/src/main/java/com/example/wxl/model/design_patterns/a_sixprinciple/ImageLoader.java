package com.example.wxl.model.design_patterns.a_sixprinciple;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created on 2016/5/7.
 *
 * @author wuxiaoliang
 * @since 1.0
 */
public class ImageLoader {
    ImageCache mImageCache = new ImageCache();
    private DiskCache mDiskCache = new DiskCache();
    private DoubleCache mDoubleCache = new DoubleCache();

    private boolean isUseDiskCache = false;
    private boolean isUseMemoryCache = false;
    private boolean isUseDoubleCache = false;

    ExecutorService mExecutorService = Executors
            .newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    public void displayImage(final String url, final ImageView imageView) {
        Bitmap bitMap = null;
        if (isUseDoubleCache) {
            bitMap = mDoubleCache.getBitMap(url);
        } else if (isUseDiskCache) {
            bitMap = mDiskCache.getBitMap(url);
        }
        if (bitMap != null) {
            imageView.setImageBitmap(bitMap);
            return;
        }
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitMap = downLoadImage(url);
                if (bitMap == null) return;
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitMap);
//                    mImageCache.putBitMap(url, bitMap);
//                    mDiskCache.putBitMap(url, bitMap);
                    mDoubleCache.putBitMap(url, bitMap);
                }
            }
        });
    }

    private Bitmap downLoadImage(String url) {
        try {
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setUseDiskCache(boolean useDiskCache) {
        this.isUseDiskCache = useDiskCache;
    }
}
