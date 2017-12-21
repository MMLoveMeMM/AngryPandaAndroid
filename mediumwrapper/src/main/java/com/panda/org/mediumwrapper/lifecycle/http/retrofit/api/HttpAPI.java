package com.panda.org.mediumwrapper.lifecycle.http.retrofit.api;

import com.panda.org.mediumwrapper.bean.HttpWeatherBean;
import com.panda.org.mediumwrapper.bean.HttpWeatherBeanT;
import com.panda.org.mediumwrapper.bean.WeatherBean;
import com.panda.org.mediumwrapper.lifecycle.http.retrofit.exp.APIException;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.panda.org.mediumwrapper.lifecycle.http.retrofit.RetrofitClient.getHttpAPI;

/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public class HttpAPI {

    public static final String URL="http://v.juhe.cn/";
    public static final MediaType RETROFIT_JSON = MediaType.parse("application/json; charset=utf-8");

    /*
    *
    * */
    public Observable<HttpWeatherBean> getBeanBodyWeather(String format, String cityname, String dtype, String key){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("format",format);
            jsonObject.put("cityname",cityname);
            jsonObject.put("dtype",dtype);
            jsonObject.put("key",key);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(RETROFIT_JSON,jsonObject.toString());
        return getHttpAPI().getBeanBodyWeather(body)
                .compose(this.<HttpWeatherBean>applySchedulers());

    }

    /*
    *
    * */
    public Observable<HttpWeatherBean> getBeanWeather(String format, String cityname, String dtype, String key){

        return getHttpAPI().getBeanWeather(format,cityname,dtype,key)
                .compose(this.<HttpWeatherBean>applySchedulers());

    }


    protected <T> Observable.Transformer<HttpWeatherBean, T> applySchedulers() {
        return (Observable.Transformer<HttpWeatherBean, T>) transformer;
    }


    final Observable.Transformer transformer = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(new Func1<HttpWeatherBean,Object>() {
                        @Override
                        public Object call(HttpWeatherBean response) {
                            return flatResponse((response));
                        }
                    });
        }
    };

    /**
     * 对网络接口返回的Response进行分割操作
     *
     * @param response
     * @param <T>
     * @return
     */
    public <T> Observable<Object> flatResponse(final HttpWeatherBeanT response) {
        return Observable.create(new Observable.OnSubscribe<Object>() {

            @Override
            public void call(Subscriber<? super Object> subscriber) {
                /*
                * 这个地方分离请求成功或失败
                * 必须是HttpWeatherBeanT基类
                * */
                if (response.getError_code()==200) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(response);
                    }
                } else {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onError(new APIException(response.getError_code(), response.getReason()));
                    }
                    return;
                }

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }

            }
        });
    }

}
