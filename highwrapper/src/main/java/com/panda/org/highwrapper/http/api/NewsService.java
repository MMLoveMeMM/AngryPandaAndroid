package com.panda.org.highwrapper.http.api;

import com.panda.org.highwrapper.bean.news.HttpNewsBean;
import com.panda.org.highwrapper.bean.news.NewsBean;
import com.panda.org.highwrapper.bean.weather.HttpWeatherBean;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rd0348 on 2017/12/21 0021.
 */

public interface NewsService {

    @POST("toutiao/index")
    Observable<HttpNewsBean> getNews(@Query("type") String format,
                                     @Query("key") String key);
}
