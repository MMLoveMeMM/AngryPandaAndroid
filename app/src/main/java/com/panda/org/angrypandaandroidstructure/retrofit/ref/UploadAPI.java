package com.panda.org.angrypandaandroidstructure.retrofit.ref;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by rd0348 on 2017/12/18 0018.
 */

public interface UploadAPI {

    /*
    * 整理上传
    * 上传单张图片
    * */
    @Multipart
    @POST("upload")
    Call<ResponseBody> upload(@Part("description") RequestBody description,
                              @Part MultipartBody.Part file);

    /*
    * 多张图片上传
    * 固定多张
    * */
    @POST("upload/")
    Call<ResponseBody> uploadFiles(@Part("filename") String description,
                                   @Part("pic\"; filename=\"image1.png") RequestBody imgs1,
                                   @Part("pic\"; filename=\"image2.png") RequestBody imgs2,
                                   @Part("pic\"; filename=\"image3.png") RequestBody imgs3);

    /*
    * 未知多张
    * 注意下面的map
    * */
    @Multipart
    @POST()
    Observable<ResponseBody> uploadFiles(
            @Url String url,
            @PartMap() Map<String, RequestBody> maps);

}
