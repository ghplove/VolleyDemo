package com.lr.ghp.utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.lr.ghp.volleydemo.R;

/**
 * Created by ghp on 7/9/2015.
 */
public class GetUrlStr {
    public static String getSiteUrl(Context context) {

        SharedPreferences appSharedPreferences = context.getSharedPreferences(
                "volleyDemo", Activity.MODE_PRIVATE);

        int site = appSharedPreferences.getInt("site", 1);

        String siteUrl = (String) context.getResources().getText(
                R.string.server_site_url);

        return siteUrl;
    }
    public static String getWebSiteUrl(Context context) {
        SharedPreferences appSharedPreferences = context.getSharedPreferences(
                "volleyDemo", Activity.MODE_PRIVATE);

        int site = appSharedPreferences.getInt("site", 1);
        String siteUrl = (String) context.getResources().getText(
                R.string.web_site_url);

        return siteUrl;
    }
}
