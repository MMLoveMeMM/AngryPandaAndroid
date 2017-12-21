package com.panda.org.highwrapper.http.api;

import com.panda.org.highwrapper.bean.weather.HttpWeatherBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rd0348 on 2017/12/21 0021.
 */
public interface WeatherService {
    @GET("weather/index")
    Observable<HttpWeatherBean> getBeanWeather(@Query("format") String format,
                                               @Query("cityname") String cityname,
                                               @Query("dtype") String dtype,
                                               @Query("key") String key);

    @Headers("Content-Type: application/json")
    @POST("weather/index")
    Observable<HttpWeatherBean> getBeanBodyWeather(@Body RequestBody body);
}
