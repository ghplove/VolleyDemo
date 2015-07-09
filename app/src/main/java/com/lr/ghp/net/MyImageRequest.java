package com.lr.ghp.net;

import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.lr.ghp.utility.CookieUtility;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ghp on 7/9/2015.
 */
public class MyImageRequest extends ImageRequest {

    public MyImageRequest(String url, Response.Listener<Bitmap> listener, int maxWidth, int maxHeight, Bitmap.Config decodeConfig, Response.ErrorListener errorListener) {
        super(url, listener, maxWidth, maxHeight, decodeConfig, errorListener);
    }

    @Override
    protected Response<Bitmap> parseNetworkResponse(NetworkResponse response) {

        Map<String, String> responseHeaders = response.headers;
        String cookies = responseHeaders.get("Set-Cookie");
        if (cookies != null) {
            Log.i("img Cookie:", cookies);
            CookieUtility.getInstance().setCookieStr(cookies);
        }
        return super.parseNetworkResponse(response);
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        //headers.put("Content-Type", "application/x-www-form-urlencoded");
        Log.i("request Cookie:", CookieUtility.getInstance().getCookie());
        headers.put("Cookie", CookieUtility.getInstance().getCookie());
        return headers;
    }


}
