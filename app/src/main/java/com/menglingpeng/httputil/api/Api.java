package com.menglingpeng.httputil.api;

import com.menglingpeng.httputil.base.BaseApi;

/**
 * Created by mengdroid on 2018/3/24.
 */

public class Api {

    private String baseUrl = "http://www.kuaidi100.com/";
    private volatile static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (Api.class) {
                if (apiService == null) {
                    new Api();
                }
            }
        }
        return apiService;
    }

    private Api() {
        BaseApi baseApi = new BaseApi();
        apiService = baseApi.getRetrofit(baseUrl).create(ApiService.class);
    }
}
