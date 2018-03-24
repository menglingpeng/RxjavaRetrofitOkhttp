package com.menglingpeng.httputil.api;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by mengdroid on 2018/3/24.
 */

public interface ApiService {
    @POST("query")
    Observable<BaseResponse<List<Login>>> login(@QueryMap Map<String, String> map);
}
