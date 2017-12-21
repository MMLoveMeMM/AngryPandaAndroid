package com.panda.org.mediumwrapper.lifecycle.http.retrofit;

import android.content.Context;

import com.panda.org.mediumwrapper.ApiService;
import com.panda.org.mediumwrapper.interceptor.BasicInterceptor;
import com.panda.org.mediumwrapper.lifecycle.http.retrofit.api.APIService;
import com.panda.org.mediumwrapper.lifecycle.http.retrofit.api.HttpAPI;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public class RetrofitClient {

    private static final int DEFAULT_TIMEOUT = 5;
    private static APIService apiService;
    private static OkHttpClient okHttpClient;
    public static String baseUrl = HttpAPI.URL;
    private static Context mContext;
    private static RetrofitClient sNewInstance;

    public static APIService getHttpAPI() {
        if (apiService == null) {
            apiService = getRetrofit();
        }
        return apiService;
    }

    private static APIService getRetrofit() {
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                //.cookieJar(new CookieManger(context))
                //.addInterceptor(new BaseInterceptor(mContext))
                .addInterceptor(BasicInterceptor.getHttpLoggingInterceptor())
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();

        return retrofit.create(APIService.class);

    }
}
