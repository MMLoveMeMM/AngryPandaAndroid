package com.panda.org.mediumwrapper;

import com.panda.org.mediumwrapper.bean.HttpWeatherBean;
import com.panda.org.mediumwrapper.bean.HttpWeatherBeanT;
import com.panda.org.mediumwrapper.bean.Result;
import com.panda.org.mediumwrapper.bean.WeatherBean;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public interface ApiService {

    public static final String Base_URL = "http://ip.taobao.com/";
    /**
     *普通写法
     */
    @GET("service/getIpInfo.php/")
    Observable<ResponseBody> getData(@Query("ip") String ip);

    @GET("{url}")
    Observable<ResponseBody> executeGet(
            @Path("url") String url,
            @QueryMap Map<String, String> maps);

    @POST("{url}")
    Observable<ResponseBody> executePost(
            @Path("url") String url,
            @FieldMap Map<String, String> maps);

    /*@Multipart
    @POST("{url}")
    Observable<ResponseBody> upLoadFile(
            @Path("url") String url,
            @Part("image\\", filename="\\image.jpg") RequestBody avatar);
    @POST("{url}")
    Call<ResponseBody> uploadFiles(
            @Url("url") String url,
            @Part("filename") String description,
            @PartMap()  Map<String, RequestBody> maps);*/

    @GET("weather/index")
    Observable<ResponseBody> getWeather(@Query("format") String format,
                                  @Query("cityname") String cityname,
                                  @Query("dtype") String dtype,
                                  @Query("key") String key);

    @GET("weather/index")
    Observable<WeatherBean> getBeanWeather(@Query("format") String format,
                                           @Query("cityname") String cityname,
                                           @Query("dtype") String dtype,
                                           @Query("key") String key);

    @GET("weather/index")
    Observable<HttpWeatherBeanT<Result>> getBeanFuncWeather(@Query("format") String format,
                                                            @Query("cityname") String cityname,
                                                            @Query("dtype") String dtype,
                                                            @Query("key") String key);

}
