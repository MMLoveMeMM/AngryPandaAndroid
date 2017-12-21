package com.panda.org.angrypandaandroidstructure.retrofit.ref;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by rd0348 on 2017/12/18 0018.
 */
/*
* 聚合网络的天气数据
* 主机地址 : http://v.juhe.cn/
* 主机地址路径 : weather/index
* 参数 : format=2&cityname=%E8%8B%8F%E5%B7%9E&key=您申请的KEY&dtype=json
* 完整路径 : http://v.juhe.cn/weather/index?format=2&cityname=%E8%8B%8F%E5%B7%9E&key=您申请的KEY&dtype=json
* */
public interface QueryAPI {
    @GET("weather/index")
    Call<ResponseBody> getWeather(@Query("format") String format,
                                  @Query("cityname") String cityname,
                                  @Query("dtype") String dtype,
                                  @Query("key") String key);

    @POST("weather/index")
    Call<ResponseBody> postWeather(@Query("format") String format,
                                  @Query("cityname") String cityname,
                                  @Query("dtype") String dtype,
                                  @Query("key") String key);

}
