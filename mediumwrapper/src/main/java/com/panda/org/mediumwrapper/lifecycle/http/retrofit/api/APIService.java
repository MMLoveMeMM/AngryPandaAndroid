package com.panda.org.mediumwrapper.lifecycle.http.retrofit.api;

import com.panda.org.mediumwrapper.bean.HttpWeatherBean;
import com.panda.org.mediumwrapper.bean.WeatherBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public interface APIService {

    @GET("weather/index")
    Observable<HttpWeatherBean> getBeanWeather(@Query("format") String format,
                                           @Query("cityname") String cityname,
                                           @Query("dtype") String dtype,
                                           @Query("key") String key);

    @Headers("Content-Type: application/json")
    @POST("weather/index")
    Observable<HttpWeatherBean> getBeanBodyWeather(@Body RequestBody body);

}
