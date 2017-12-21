package com.panda.org.highwrapper.http.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by rd0348 on 2017/12/21 0021.
 * 这个会在参数列表中添加固定的parameters
 */

public class BodyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        Request request;
//        String method = originRequest.method();
//        Headers headers = originRequest.headers();
        HttpUrl httpUrl = originRequest.url().newBuilder()
                .addQueryParameter("cityname", "深圳")
                .addQueryParameter("key", "3a8c66214031c01135f509434ae44b84")
                .build();
        request = originRequest.newBuilder().url(httpUrl).build();
        return chain.proceed(request);
    }
}
