package com.panda.org.angrypandaandroidstructure.retrofit.run;

import android.content.Context;
import android.util.Log;

import com.panda.org.angrypandaandroidstructure.retrofit.ref.BasicInterceptor;
import com.panda.org.angrypandaandroidstructure.retrofit.ref.CacheProvide;
import com.panda.org.angrypandaandroidstructure.retrofit.ref.HeaderAPI;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public class Okhttp4Thread extends Thread{

    private final static String TAG="Okhttp4Thread";

    private Context mContext;
    public Okhttp4Thread(Context context){
        mContext=context;
    }
    @Override
    public void run() {
        super.run();

        HttpLoggingInterceptor logging = BasicInterceptor.getHttpLoggingInterceptor();

        Interceptor interceptor=BasicInterceptor.getParamsInterceptor();

        Interceptor cacheInterceptor =BasicInterceptor.getCacheInterceptor();
        //设置日志拦截器
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();

        OkHttpClient client = new OkHttpClient.Builder()
                //设置缓存
                .cache(new CacheProvide(mContext).provideCache())
                //log请求参数
                .addInterceptor(logging)
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                //网络请求缓存，未实现
                .addInterceptor(cacheInterceptor)
                .build();

        setRetry(client.newBuilder());
        setConnecTimeout(client.newBuilder());

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

    public void setRetry(OkHttpClient.Builder builder) {
        builder.retryOnConnectionFailure(true);
    }

    public void setConnecTimeout(OkHttpClient.Builder builder) {
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

    }

}
