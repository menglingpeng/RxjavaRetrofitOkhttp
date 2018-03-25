package com.menglingpeng.httputil.progress;

/**
 * Created by mengdroid on 2018/3/25.
 */

public interface ObserverResponseListener<T> {
    /**
     * 响应成功
     * @param t
     */
    void onNext(T t);

    /**
     * 响应失败
     * @param e
     */
    void onError(Exception e);
}
