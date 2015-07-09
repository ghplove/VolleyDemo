package com.lr.ghp.utility;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by ghp on 7/9/2015.
 */
public class MemoryCache implements ImageLoader.ImageCache {
    private LruCache<String, Bitmap> mCache;

    public MemoryCache() {
        //这个取单个应用最大使用内存的1/8
        int maxSize = (int) Runtime.getRuntime().maxMemory() / 8;
        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //这个方法一定要重写，不然缓存没有效果
                return value.getRowBytes()*value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }
}

