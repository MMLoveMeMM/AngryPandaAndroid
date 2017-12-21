package com.panda.org.mediumwrapper;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.panda.org.mediumwrapper.bean.HttpWeatherBean;
import com.panda.org.mediumwrapper.bean.HttpWeatherBeanT;
import com.panda.org.mediumwrapper.bean.Result;
import com.panda.org.mediumwrapper.bean.WeatherBean;
import com.panda.org.mediumwrapper.cookie.CookieManger;
import com.panda.org.mediumwrapper.exception.ApiException;
import com.panda.org.mediumwrapper.interceptor.BaseInterceptor;
import com.panda.org.mediumwrapper.interceptor.BasicInterceptor;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public class RetrofitClients {

    private static final int DEFAULT_TIMEOUT = 5;
    private ApiService apiService;
    private OkHttpClient okHttpClient;
    public static String baseUrl = ApiService.Base_URL;
    private static Context mContext;
    private static RetrofitClients sNewInstance;
    private static class SingletonHolder {
        private static RetrofitClients INSTANCE = new RetrofitClients(
                mContext);
    }
    public static RetrofitClients getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return SingletonHolder.INSTANCE;
    }
    public static RetrofitClients getInstance(Context context, String url) {
        if (context != null) {
            mContext = context;
        }
        sNewInstance = new RetrofitClients(context, url);
        return sNewInstance;
    }
    private RetrofitClients(Context context) {
        this(context, null);
    }
    private RetrofitClients(Context context, String url) {
        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }
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
                .baseUrl(url)
                .build();
        apiService = retrofit.create(ApiService.class);
    }
    public void getData(Subscriber<ResponseBody> subscriber, String ip) {
        apiService.getData(ip)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);//通知订阅者
    }
    public void get(String url, Map headers, Map parameters, Subscriber<ResponseBody> subscriber) {
        apiService.executeGet(url, headers)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public void post(String url, Map headers, Map parameters, Subscriber<ResponseBody> subscriber) {
        apiService.executePost(url, parameters)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getWeather(Subscriber<ResponseBody> subscriber,
                           String format,
                           String cityname,
                           String dtype,
                           String key){
        apiService.getWeather(format,cityname,dtype,key)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getBeanWeather(Subscriber<WeatherBean> subscriber,
                               String format,
                               String cityname,
                               String dtype,
                               String key){
        apiService.getBeanWeather(format,cityname,dtype,key)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getBeanFuncWeather(Subscriber<HttpWeatherBeanT> subscriber,
                                   String format,
                                   String cityname,
                                   String dtype,
                                   String key){
        apiService.getBeanFuncWeather(format,cityname,dtype,key)
                //.map(new HttpResultFunc<HttpWeatherBeanT<Result>>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


}

