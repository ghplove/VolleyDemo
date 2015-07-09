package com.lr.ghp.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.lr.ghp.Model.errorModel.LoginError;
import com.lr.ghp.Model.errorModel.LoginUserError;
import com.lr.ghp.Model.responseModel.LoginResponse;
import com.lr.ghp.Model.responseModel.LoginUser;
import com.lr.ghp.app.MyApplication;
import com.lr.ghp.net.MyGsonRequest;
import com.lr.ghp.utility.NetWorkStatus;
import com.lr.ghp.utility.VolleyErrorHelper;

import de.greenrobot.event.EventBus;

/**
 * Created by ghp on 7/9/2015.
 */
public class UserNetWork {
    private Context context;
    private RequestQueue requestQueue=null;

    public UserNetWork(Context context) {
        this.context = context;
        requestQueue= MyApplication.getInstance().getRequestQueue();
        NetWorkStatus.setRquestTimeout(context);
    }

    public void userLogin(String url,String userName,String pwd,String code){
        MyGsonRequest request=new MyGsonRequest<LoginResponse>(Request.Method.POST,url,LoginResponse.class,new Response.Listener<LoginResponse>(){

            @Override
            public void onResponse(LoginResponse response) {
                EventBus.getDefault().post(response);
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                EventBus.getDefault().post(new LoginError(VolleyErrorHelper.getMessage(error, context)));
            }
        });
        request.setParam("username", userName);
        request.setParam("password", pwd);
        request.setParam("captcha", code);
        requestQueue.add(request);

    }
    public void getLoginUserInfo(String url){
        MyGsonRequest request=new MyGsonRequest<LoginUser>(Request.Method.GET,url,LoginUser.class,new Response.Listener<LoginUser>(){

            @Override
            public void onResponse(LoginUser loginUser) {
                EventBus.getDefault().postSticky(loginUser);//重复请求，保留最新
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                EventBus.getDefault().post(new LoginUserError(VolleyErrorHelper.getMessage(error,context)));
            }
        });
        requestQueue.add(request);
    }
}
