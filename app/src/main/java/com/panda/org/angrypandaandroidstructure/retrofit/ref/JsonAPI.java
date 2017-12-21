package com.panda.org.angrypandaandroidstructure.retrofit.ref;

import com.panda.org.angrypandaandroidstructure.retrofit.bean.ApiInfo;
import com.panda.org.angrypandaandroidstructure.retrofit.bean.JsonInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by rd0348 on 2017/12/18 0018.
 *
 */

public interface JsonAPI {

    @POST("client/shipper/getCarType")
    Call<ResponseBody> getUserInfo(@Body JsonInfo jsonInfo);
}
