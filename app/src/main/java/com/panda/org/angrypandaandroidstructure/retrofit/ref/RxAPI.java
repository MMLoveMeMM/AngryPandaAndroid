package com.panda.org.angrypandaandroidstructure.retrofit.ref;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by rd0348 on 2017/12/18 0018.
 */

public interface RxAPI {

    @Headers("Content-Type: application/json")
    @POST()
    Observable<ResponseBody> updateInfo(
            @Url String url,
            @Body() RequestBody body);

}
