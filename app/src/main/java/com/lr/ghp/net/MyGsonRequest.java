package com.lr.ghp.net;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.lr.ghp.utility.CookieUtility;
import com.lr.ghp.utility.NetWorkStatus;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ghp on 7/9/2015.
 */
public class MyGsonRequest<T> extends Request<T> {
    private final Response.Listener<T> mListener;
    private Gson mGson;

    private Class<T> mClass;
    private int methodType;
    private Map<String, String> mMap;
    private Type mTypeToken;


    /**
     *
     * @param method 发送方法Method.GET/Method.POST
     * @param url 请求网络url
     * @param clazz 数据请求成功后要反编译成的类
     * @param listener 成功后的监听
     * @param errorListener 失败后的监听
     */
    public MyGsonRequest(int method, String url, Class<T> clazz, Response.Listener<T> listener,
                         Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        methodType=method;
        mGson = new Gson();
        mClass = clazz;
        mListener = listener;
        mMap=new HashMap<String, String>();
    }

    /**
     *
     * @param url 默认get请求
     * @param clazz
     * @param listener
     * @param errorListener
     */
    public MyGsonRequest(String url, Class<T> clazz, Response.Listener<T> listener,
                         Response.ErrorListener errorListener) {
        this(Method.GET, url, clazz, listener, errorListener);
    }

    /**
     *
     * @param url
     * @param typeToken 构造函数主要针对返回的数据之间是个数组数据的时候要用到
     * @param listener
     * @param errorListener
     */
    public MyGsonRequest(String url, Type typeToken, Response.Listener<T> listener, Response.ErrorListener errorListener) {

        this(Method.GET, url, null, listener, errorListener);
        mTypeToken = typeToken;
    }

    public MyGsonRequest(int method, String url, Type typeToken, Response.Listener<T> listener, Response.ErrorListener errorListener) {

        this(method, url, null, listener, errorListener);
        mTypeToken = typeToken;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        Map<String, String> responseHeaders = response.headers;
        String cookies = responseHeaders.get("Set-Cookie");
        if (!TextUtils.isEmpty(cookies)) {
            CookieUtility.getInstance().setCookieStr(cookies);
        }
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.d("response:", jsonString);
            if (mTypeToken == null) {
                if (mClass.equals(String.class)) {
                    return (Response<T>) Response.success(jsonString,
                            HttpHeaderParser.parseCacheHeaders(response));
                } else {
                    return Response.success(mGson.fromJson(jsonString, mClass),
                            HttpHeaderParser.parseCacheHeaders(response));
                }
            }
            else{
                return (Response<T>) Response.success(mGson.fromJson(jsonString, mTypeToken),
                        HttpHeaderParser.parseCacheHeaders(response));
            }
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if(methodType== Method.POST){
            return mMap;
        }
        return super.getParams();
    }

    public void setParam(String key, String value) {
        mMap.put(key, value);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        //headers.put("Content-Type", "application/x-www-form-urlencoded");
        Log.i("request Cookie:", CookieUtility.getInstance().getCookie());
        headers.put("Cookie", CookieUtility.getInstance().getCookie());
        return headers;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        //打印发送到后台的数据信息
//        Logger.i("request message:",new String(super.getBody()));
        return super.getBody();
    }

    @Override
    public int getMethod() {
        Log.i("request method:", super.getMethod() == 1 ? "post" : "get");
        return super.getMethod();
    }

    /**
     * 设置请求失败后，不再去尝试请求在源码中添加了下面方法
     * DefaultRetryPolicy(int initialTimeoutMs, int maxNumRetries, float backoffMultiplier)
     * @param retryPolicy
     * @return
     */
    @Override
    public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
        int initialTimeoutMs= NetWorkStatus.initialTimeoutMs;
        return super.setRetryPolicy(new DefaultRetryPolicy(initialTimeoutMs,0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }


}

