package com.panda.org.angrypandaandroidstructure.retrofit.ref;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rd0348 on 2017/12/18 0018.
 */

public interface HeaderAPI {
    @GET("weather/index")
    Call<ResponseBody> getWeather(/*@Query("format") String format,

                                  @Query("dtype") String dtype,*/
                                  @Query("cityname") String cityname,
                                  @Query("key") String key);

    @GET("weather/index")
    Call<ResponseBody> getNoWeather();

}
