package com.panda.org.mediumwrapper.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by rd0348 on 2017/12/19 0019.
 */

public class BasicInterceptor {

    public static Interceptor getRequestHeader() {
        Interceptor headerInterceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder();
                /*
                * 增加每个请求固定提交的参数
                * */
                builder.header("format", "2");
                //builder.header("cityname", "21");
                builder.header("dtype", "json");
                //builder.header("key", "3a8c66214031c01135f509434ae44b84");

                Request.Builder requestBuilder =builder.method(originalRequest.method(), originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }

        };

        return headerInterceptor;
    }

    public static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    public static Interceptor getResponseHeader() {
        Interceptor interceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                String timestamp = response.header("time");
                if (timestamp != null) {
                    //获取到响应header中的time
                }
                return response;
            }
        };
        return interceptor;
    }

    public static Interceptor getParamsInterceptor() {
        Interceptor commonParams = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originRequest = chain.request();
                Request request;
//                String method = originRequest.method();
//                Headers headers = originRequest.headers();
                HttpUrl httpUrl = originRequest.url().newBuilder()
                        .addQueryParameter("cityname", "深圳")
                        .addQueryParameter("key", "3a8c66214031c01135f509434ae44b84")
                        .build();
                request = originRequest.newBuilder().url(httpUrl).build();
                return chain.proceed(request);
            }
        };

        return commonParams;
    }

    public static Interceptor getCacheInterceptor(){
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                String cache = request.header("Cache-Time");
                if (cache!=null) {//缓存时间不为空
                    Response response1 = response.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            //cache for cache seconds
                            .header("Cache-Control", "max-age="+cache)
                            .build();
                    return response1;
                } else {
                    return response;
                }
            }
        };

        return cacheInterceptor;
    }

    public class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            String cache = request.header("Cache-Time");
            if (cache!=null) {//缓存时间不为空
                Response response1 = response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        //cache for cache seconds
                        .header("Cache-Control", "max-age="+cache)
                        .build();
                return response1;
            } else {
                return response;
            }
        }
    }

}
