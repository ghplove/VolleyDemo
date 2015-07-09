package com.lr.ghp.utility;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.lr.ghp.app.MyApplication;

import org.apache.http.Header;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ghp on 7/9/2015.
 */
public class CookieUtility {

    private static final String[] COOKIES={".JM.AUTH","ForumTicket","juhs","Version","abs","Path","tr","Domain","Expires"};
    private Map<String,String> cookie;

    private static CookieUtility instance;
    private CookieUtility(){
        cookie =new HashMap<String,String>();
    }

    public static CookieUtility getInstance(){
        if(null==instance){
            instance=new CookieUtility();
        }
        return instance;
    }

    public void setCookies(List<Header> headers){
        for(Header header:headers){
            String cookieName=(String)header.getName();
            String compare="Set-Cookie";
            if(cookieName!=null&& cookieName.contains(compare))
            {
                String cookie = header.getValue();
                setCookieStr(cookie);
            }
        }

    }

    public String getCookie(){
        String sessionStr=null;
        if (!cookie.isEmpty()){
            Iterator<Map.Entry<String, String>> it = cookie.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                if(null==sessionStr){
                    sessionStr=entry.getKey()+"="+entry.getValue()+";";
                    if(sessionStr.startsWith(" ")){
                        sessionStr=sessionStr.substring(1,sessionStr.length());
                    }
                }else{
                    String subStr=entry.getKey()+"="+entry.getValue()+";";
                    if(subStr.startsWith(" ")){
                        sessionStr=sessionStr+subStr;
                    }else{
                        sessionStr=sessionStr+" "+subStr;
                    }
                }
            }
            if(!TextUtils.isEmpty(sessionStr)&&sessionStr.endsWith(";")){
                sessionStr=sessionStr.substring(0,sessionStr.length()-1);
            }
        }
        if(TextUtils.isEmpty(sessionStr)){
            SharedPreferences sharedPreferences= MyApplication.getJimuLoanApplicationInstance().getSharedPreferences("volleyDemo", Activity.MODE_PRIVATE);
            sessionStr = sharedPreferences.getString("cachCookie","");
        }
        Log.v("sessionStr======",sessionStr+"");
        return sessionStr;
    }
    public String getCookiesStr(){
        String sessionStr=null;
        for (int i=0;i<COOKIES.length;i++){
            if(null==sessionStr){
                if(null!= cookie.get(COOKIES[i])){
                    sessionStr=COOKIES[i]+"="+ cookie.get(COOKIES[i])+";";
                    if(sessionStr.startsWith(" ")){
                        sessionStr=sessionStr.substring(1,sessionStr.length());
                    }
                }
            }else{
                if(null!= cookie.get(COOKIES[i])){
                    String subStr=COOKIES[i]+"="+ cookie.get(COOKIES[i])+";";
                    if(subStr.startsWith(" ")){
                        sessionStr=sessionStr+subStr;
                    }else{
                        sessionStr=sessionStr+" "+subStr;
                    }
                }
            }
        }
        if(!TextUtils.isEmpty(sessionStr)&&sessionStr.endsWith(";")){
            sessionStr=sessionStr.substring(0,sessionStr.length()-1);
        }
        if(TextUtils.isEmpty(sessionStr)){
            SharedPreferences sharedPreferences= MyApplication.getJimuLoanApplicationInstance().getSharedPreferences("volleyDemo", Activity.MODE_PRIVATE);
            sessionStr = sharedPreferences.getString("cachCookie","");
        }
        Log.v("sessionStr======",sessionStr+"");
        return sessionStr;
    }

    public String getCookiesAndSession(){

        return null;
    }

    public void setCookies(Header[] headers){
        for(Header header:headers){
            String cookieName=(String)header.getName();
            String compare="Set-Cookie";
            if(cookieName!=null&& cookieName.contains(compare))
            {
                String cookie = header.getValue();
                setCookieStr(cookie);
            }
        }
    }

    public void setCookieStr(String cookie) {

        String[] cookieItems = cookie.split(";");
        for (int j = 0; j < cookieItems.length; j++) {
            if(cookieItems[j].contains("=")){
                int index=cookieItems[j].indexOf("=");

                /** 修改cookies */
                String name=cookieItems[j].substring(0,index);
                String body=cookieItems[j].substring(index+1,cookieItems[j].length());
//                String[] cookieItem=cookieItems[j].split("=");
//                cookie.put(cookieItem[0],cookieItem[1]);
                this.cookie.put(name, body);
//                /** 添加cookie for php*/
//                if(cookieItem[0].contains(".JM.AUTH")){
//                    cookie.put("activity_auth",cookieItem[1]);
//                }
            }
        }
        //存储到sharedpreferences，以防seesion被系统清理掉
        SharedPreferences sharedPreferences= MyApplication.getJimuLoanApplicationInstance().getSharedPreferences("volleyDemo", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("cachCookie", getCookie());
        editor.commit();
    }

    public void clear(){
        if(!cookie.isEmpty()){
            cookie.clear();
        }
    }


    public void clearCookies(){
        if(!cookie.isEmpty()){
            cookie.clear();
            SharedPreferences sharedPreferences= MyApplication.getJimuLoanApplicationInstance().getSharedPreferences("volleyDemo", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("cachCookie","");
            editor.commit();
        }
    }

}
