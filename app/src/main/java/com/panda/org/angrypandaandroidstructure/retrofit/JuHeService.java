package com.panda.org.angrypandaandroidstructure.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rd0348 on 2017/12/18 0018.
 */

public interface JuHeService {

    @GET("weather/index")
    Call<ResponseBody> getWeather(@Query("format") String format,
                                  @Query("cityname") String cityname,
                                  @Query("dtype") String dtype,
                                  @Query("key") String key);
}
