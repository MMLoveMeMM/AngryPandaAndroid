package com.panda.org.angrypandaandroidstructure.retrofit.ref;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rd0348 on 2017/12/18 0018.
 */
/*
* 主机地址 : http://www.weather.com.cn/
* 主机上的路径 : adat/sk/{cityId}.html
* 城市ID : 101010100
*
* */
public interface PathAPI {

    @GET("adat/sk/{cityId}.html")
    Call<ResponseBody> getWeather(@Path("cityId") String cityId);

}
