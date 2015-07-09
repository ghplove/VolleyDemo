package com.lr.ghp.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by ghp on 7/9/2015.
 */
public class NetWorkStatus {
    public static int initialTimeoutMs;
    public static final int NETWORKTYPE_UNKNOWN=0;
    public static final int NETWORKTYPE_WAP=1;
    public static final int NETWORKTYPE_WIFI=2;
    public static final int NETWORKTYPE_4G=3;
    public static final int NETWORKTYPE_3G=4;
    public static final int NETWORKTYPE_2G=5;
    //获取网络的状态
    public static int  getNetWorkStatus(Context context){
        int mNetWorkType=0;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager!=null){
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null&&networkInfo.isConnected()){
                String type=networkInfo.getTypeName();
                if(type.equalsIgnoreCase("WIFI")){
                    mNetWorkType=NETWORKTYPE_WIFI;
                }else if(type.equalsIgnoreCase("MOBILE")){
                    String proxyHost=android.net.Proxy.getDefaultHost();
                    mNetWorkType=isFastMobileNetWork(context);
                }
            }
        }
        return mNetWorkType;
    }

    private static int isFastMobileNetWork(Context context){
        TelephonyManager telephonyManager= (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType=telephonyManager.getNetworkType();
        switch (networkType){
            case TelephonyManager.NETWORK_TYPE_1xRTT://50-100kbps
            case TelephonyManager.NETWORK_TYPE_CDMA://14-64kbps
            case TelephonyManager.NETWORK_TYPE_EDGE://50-100kbps
            case TelephonyManager.NETWORK_TYPE_IDEN://25kbps
            case TelephonyManager.NETWORK_TYPE_GPRS://100kbps
                return NETWORKTYPE_2G;
            case TelephonyManager.NETWORK_TYPE_EVDO_0://400-1000kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A://600-1400kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA://2-14Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA://700-1700kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA://1-23Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS://400-700kbps
            case TelephonyManager.NETWORK_TYPE_EHRPD://1-2Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B://5Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP://10-20Mbps
                return NETWORKTYPE_3G;
            case TelephonyManager.NETWORK_TYPE_LTE://10+Mbps
                return NETWORKTYPE_4G;
            default:
                return NETWORKTYPE_UNKNOWN;
        }
    }
    public static void setRquestTimeout(Context context){
         int mNetWorkType=getNetWorkStatus(context);
        switch (mNetWorkType){
            case NETWORKTYPE_WIFI:
                initialTimeoutMs=10000;
                break;
            case NETWORKTYPE_4G:
                initialTimeoutMs=20000;
                break;
            case NETWORKTYPE_3G:
                initialTimeoutMs=40000;
                break;
            case NETWORKTYPE_2G:
                initialTimeoutMs=60000;
                break;
            default:
                break;
        }
    }
}
