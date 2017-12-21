package com.panda.org.highwrapper.http.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by rd0348 on 2017/12/21 0021.
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();
        /*
        * 增加每个请求固定参数到http请求的头部
        * */
        //builder.header("format", "2");
        //builder.header("cityname", "21");
        //builder.header("dtype", "json");
        //builder.header("key", "3a8c66214031c01135f509434ae44b84");

        Request.Builder requestBuilder =builder.method(originalRequest.method(), originalRequest.body());
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
