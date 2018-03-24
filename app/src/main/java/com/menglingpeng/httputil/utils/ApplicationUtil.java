package com.menglingpeng.httputil.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by mengdroid on 2018/3/24.
 */

public class ApplicationUtil extends Application {

    private static Context mContext;//全局上下文对象

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
