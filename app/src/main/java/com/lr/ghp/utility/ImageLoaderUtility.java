package com.lr.ghp.utility;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.lr.ghp.app.MyApplication;
import com.lr.ghp.net.MyImageRequest;

/**
 * Created by ghp on 7/9/2015.
 */
public class ImageLoaderUtility {
    private static MemoryCache memoryCache=new MemoryCache();
    //主要用于请求url不变的时候，调用这个方法去获取图片
    //该方法用于get验证码图片
    public static void loadImg( final ImageView imageView,String imgUrl, final ProgressBar codeProgressBar, final TextView codeTip){
        MyImageRequest imageRequest = new MyImageRequest(
                imgUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                        codeProgressBar.setVisibility(View.GONE);
                        codeTip.setVisibility(View.GONE);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //imageView.setImageResource(R.drawable.ic_launcher);
                codeProgressBar.setVisibility(View.GONE);
                codeTip.setVisibility(View.VISIBLE);
            }
        });
        MyApplication.getInstance().getRequestQueue().add(imageRequest);
    }
    //普通图片下载，通过LruCache缓存，如果第二次加载，直接从本地获取。
    public static void loadImgLruCache(ImageView imageView,String imgUrl){
        ImageLoader imageLoader = new ImageLoader(MyApplication.getInstance().getRequestQueue(),memoryCache);
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                0, 0);
        imageLoader.get(imgUrl,listener);

    }
}
