package com.menglingpeng.httputil.http;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by mengdroid on 2018/3/23.
 */

public interface HttpService {
    @POST("rxjava")
    Observable<Retrofit> getAll(@Body boolean once_no);
}
