package com.lr.ghp.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.lr.ghp.Model.ApiUpdate;
import com.lr.ghp.Model.User;
import com.lr.ghp.utility.MemoryCache;

import java.io.File;
import java.util.Date;

/**
 * Created by ghp on 15/7/9.
 */
public class MyApplication extends Application{
    private boolean firstStart = true;
    private User user;
    private boolean isLogin = false;
    private String longToken;
    private String token;
    private Date lastOperationTime;
    private ApiUpdate apiUpdate;
    private boolean isFront;
    public static MyApplication app;
    //使用单例模式
    public static MyApplication getInstance(){
        return app;
    }
    //请求队列
    private RequestQueue requestQueue;
    //图片请求
    private ImageLoader imageLoader;

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        //初始化内存缓存目录
        File cacheDir = new File(this.getCacheDir(), "volley");
        /**
         初始化RequestQueue,其实这里你可以使用Volley.newRequestQueue来创建一个RequestQueue,直接使用构造函数可以定制我们需要的RequestQueue,比如线程池的大小等等
         */
        requestQueue=new RequestQueue(new DiskBasedCache(cacheDir), new BasicNetwork(new HurlStack()), 3);

        //初始化图片内存缓存
        MemoryCache mCache=new MemoryCache();
        //初始化ImageLoader
        imageLoader =new ImageLoader(requestQueue,mCache);
        //如果调用Volley.newRequestQueue,那么下面这句可以不用调用
        requestQueue.start();
    }

    public static MyApplication getJimuLoanApplicationInstance(){
        if(null==app){
            app=new MyApplication();
        }
        return app;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the firstStart
     */
    public boolean isFirstStart() {
        return firstStart;
    }

    /**
     * @param firstStart the firstStart to set
     */
    public void setFirstStart(boolean firstStart) {
        this.firstStart = firstStart;
    }
    public Boolean getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Boolean isLogin) {
        this.isLogin = isLogin;
    }
    /**
     * @return the longToken
     */
    public String getLongToken() {
        return longToken;
    }

    /**
     * @param longToken the longToken to set
     */
    public void setLongToken(String longToken) {
        this.longToken = longToken;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastOperationTime() {
        return lastOperationTime;
    }

    public void setLastOperationTime(Date lastOperationTime) {
        this.lastOperationTime = lastOperationTime;
    }

    public boolean isFront() {
        return isFront;
    }

    public void setIsFront(boolean isFront) {
        this.isFront = isFront;
    }

    public ApiUpdate getApiUpdate() {
        return apiUpdate;
    }

    public void setApiUpdate(ApiUpdate apiUpdate) {
        this.apiUpdate = apiUpdate;
    }
}
