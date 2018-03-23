package com.menglingpeng.httputil.http;

import com.menglingpeng.httputil.utils.Constants;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mengdroid on 2018/1/14.
 */

public class RetrofitFactory {
    private static RetrofitFactory retrofitFactory;

    public static RetrofitFactory getInstence(HttpUrl httpUrl){
        if(retrofitFactory == null){
            synchronized (RetrofitFactory.class){
                if(retrofitFactory == null){
                    retrofitFactory = new RetrofitFactory(httpUrl);
                }
            }
        }
        return retrofitFactory;
    }

    private RetrofitFactory(HttpUrl httpUrl){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Constants.HTTP_CONFIG_HTTP_CONNECT_TIME, TimeUnit.SECONDS)
                .readTimeout(Constants.HTTP_CONFIG_HTTP_READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(Constants.HTTP_CONFIG_HTTP_WRITE_TIME, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加Rxjava转换器
                .addConverterFactory(GsonConverterFactory.create())//添加Gson转换器
                .client(client)
                .build();
    }
}
