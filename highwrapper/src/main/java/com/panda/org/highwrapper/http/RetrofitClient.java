package com.panda.org.highwrapper.http;

import android.content.Context;

import com.panda.org.highwrapper.http.api.NewsAPI;
import com.panda.org.highwrapper.http.api.NewsService;
import com.panda.org.highwrapper.http.api.WeatherAPI;
import com.panda.org.highwrapper.http.api.WeatherService;
import com.panda.org.highwrapper.http.interceptor.CacheInterceptor;
import com.panda.org.highwrapper.http.interceptor.CacheProvide;
import com.panda.org.highwrapper.http.interceptor.LogInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
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
    private static WeatherService apiService;
    private static NewsService newsService;
    private static OkHttpClient okHttpClient;
    public static String weatherUrl = WeatherAPI.URL;
    public static String newsUrl = NewsAPI.URL;
    private static Context mContext;

    public static WeatherService getWeatherAPI() {
        if (apiService == null) {
            apiService = getWeatherRetrofit();
        }
        return apiService;
    }

    public static NewsService getNewsAPI() {
        if (newsService == null) {
            newsService = getNewsRetrofit();
        }
        return newsService;
    }

    private static WeatherService getWeatherRetrofit() {
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                //.cache(new Cache(new CacheProvide(context).provideCache()))
                //.addInterceptor(new CacheInterceptor())
                .addInterceptor(LogInterceptor.getHttpLoggingInterceptor())
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(weatherUrl)
                .build();

        return retrofit.create(WeatherService.class);
    }

    private static NewsService getNewsRetrofit() {
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                //.cache(new Cache(new CacheProvide(context).provideCache()))
                //.addInterceptor(new CacheInterceptor())
                .addInterceptor(LogInterceptor.getHttpLoggingInterceptor())
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(newsUrl)
                .build();

        return retrofit.create(NewsService.class);
    }



}
