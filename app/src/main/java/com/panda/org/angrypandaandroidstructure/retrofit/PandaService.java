package com.panda.org.angrypandaandroidstructure.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rd0348 on 2017/12/18 0018.
 */

public interface PandaService {

    @GET("adat/sk/{cityId}.html")
    Call<ResponseBody> getWeather(@Path("cityId") String cityId);

}
