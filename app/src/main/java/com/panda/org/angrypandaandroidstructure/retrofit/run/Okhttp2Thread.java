package com.panda.org.angrypandaandroidstructure.retrofit.run;

import android.util.Log;

import com.panda.org.angrypandaandroidstructure.retrofit.ref.BasicInterceptor;
import com.panda.org.angrypandaandroidstructure.retrofit.ref.HeaderAPI;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by rd0348 on 2017/12/19 0019.
 */

public class Okhttp2Thread extends Thread {

    private final static String TAG="Okhttp2Thread";

    @Override
    public void run() {
        super.run();

        HttpLoggingInterceptor logging = BasicInterceptor.getHttpLoggingInterceptor();

        Interceptor interceptor=BasicInterceptor.getParamsInterceptor();
        //设置日志拦截器
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();

        OkHttpClient client = new OkHttpClient.Builder()
                //设置缓存
                //      .cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024))
                //log请求参数
                .addInterceptor(logging)
                .addInterceptor(interceptor)
                //网络请求缓存，未实现
                //    .addInterceptor(cacheInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
                .baseUrl("http://v.juhe.cn/")
                .client(client)
                .build();
        HeaderAPI apiStores = retrofit.create(HeaderAPI.class);
        Call<ResponseBody> call = apiStores.getNoWeather();// 注意:参数在固定头部中

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i(TAG, "response=" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "onFailure=" + t.getMessage());
            }

        });

    }

}
