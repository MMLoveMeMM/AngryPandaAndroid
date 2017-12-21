package com.panda.org.angrypandaandroidstructure.retrofit;

import com.panda.org.angrypandaandroidstructure.retrofit.bean.ApiInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by rd0348 on 2017/12/18 0018.
 */

public interface BeanService {
    @POST("client/shipper/getCarType")
    Call<ResponseBody> getCarType(@Body ApiInfo apiInfo);
}
