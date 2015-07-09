package com.lr.ghp.utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lr.ghp.Model.User;
import com.lr.ghp.app.MyApplication;
import com.lr.ghp.constant.URLConstant;
import com.lr.ghp.network.UserNetWork;

/**
 * Created by ghp on 7/9/2015.
 */
public class CommonUtility {
    /**
     * 退出清除user相关消息
     */
    public static void logout(Context context) {
        MyApplication app = (MyApplication) context.getApplicationContext();
        app.setUser(null);
        app.setToken(null);
        app.setIsLogin(false);

        CookieUtility.getInstance().clearCookies();
        SharedPreferences appSharedPreferences = context.getSharedPreferences(
                "volleyDemo", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = appSharedPreferences.edit();
        editor.remove("username");
        editor.remove("token");
        editor.remove("gesture");
        editor.apply();
    }

    /**
     * 设置user信息
     * @param user
     * @param context
     */
    public static void saveUser(User user,Context context){
        MyApplication app = (MyApplication) context.getApplicationContext();
        app.setIsLogin(true);
        app.setUser(user);
        SharedPreferences appSharedPreferences = context.getSharedPreferences(
                "volleyDemo", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = appSharedPreferences.edit();
        editor.putString("username", user.getUserName());
        editor.apply();
    }

    /**
     * 获取验证码图片
     * @param activity
     * @param imgCode
     * @param codeProgressBar
     * @param codeTip
     */
     public static void LoadCodeImage(Activity activity,ImageView imgCode,ProgressBar codeProgressBar,TextView codeTip){
        codeProgressBar.setVisibility(View.VISIBLE);
        codeTip.setVisibility(View.GONE);
        imgCode.setImageBitmap(null);
        String siteUrl = GetUrlStr.getSiteUrl(activity);
        String imageUrl = siteUrl + URLConstant.getCaptchaUrl();
        ImageLoaderUtility.loadImg(imgCode, imageUrl, codeProgressBar, codeTip);
    }

    /**
     * 显示错误信息
     * @param showErrorText
     * @param errorString
     */
    public static void showErrorString(TextView showErrorText, String errorString){
        showErrorText.setText(errorString);
        showErrorText.setVisibility(View.VISIBLE);

    }

    /**
     * 验证字符串是否符合要求
     * @param source
     * @param reg
     * @return
     */
    public static boolean checkString(String source, String reg) {
        return source.matches(reg);
    }

    //获取LoginUser
    public final static void refreshUser(Context context){
        UserNetWork userDao=new UserNetWork(context);
        String siteUrl = GetUrlStr.getSiteUrl(context);
        userDao.getLoginUserInfo(siteUrl + URLConstant.getUserInfoUrl());
    }
}
