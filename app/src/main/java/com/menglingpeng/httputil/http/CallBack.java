package com.menglingpeng.httputil.http;

/**
 * Created by mengdroid on 2018/4/4.
 */

public abstract class CallBack {

    public void onStart(){}

    public void onCompleted(){}

    abstract public void onError(Throwable e);

    public void onProgress(long fileSizeDownloaded){}

    abstract public void onSucess(String path, String name, long fileSize);
}
