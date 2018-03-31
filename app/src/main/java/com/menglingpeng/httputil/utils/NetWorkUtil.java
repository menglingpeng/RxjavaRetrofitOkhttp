package com.menglingpeng.httputil.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by mengdroid on 2018/3/25.
 */

public class NetWorkUtil {

    /**
     * 判断网络状态
     */
    public static boolean isNetConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 返回当前网络状态
     */
    public static int getNetState(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo networkinfo = connectivity.getActiveNetworkInfo();
                if (networkinfo != null) {
                    if (networkinfo.isAvailable() && networkinfo.isConnected()) {
                        if (!isNetConnected(context)){
                            return Constants.NET_CONNECTION_TIME_OUT;
                        }
                        else{
                            return Constants.NET_CONNECTION_OK;
                        }
                    } else {
                        return Constants.NET_NOT_READY;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Constants.NET_ERROR;
    }

}
