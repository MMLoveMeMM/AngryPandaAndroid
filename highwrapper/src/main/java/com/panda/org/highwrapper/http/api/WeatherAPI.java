package com.panda.org.highwrapper.http.api;

import com.panda.org.highwrapper.bean.weather.BaseWeatherBean;
import com.panda.org.highwrapper.bean.weather.HttpWeatherBean;
import com.panda.org.highwrapper.http.exp.NetException;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.panda.org.highwrapper.http.RetrofitClient.getWeatherAPI;

/**
 * Created by rd0348 on 2017/12/21 0021.
 */

public class WeatherAPI {

    public static final String URL="http://v.juhe.cn/";
    public static final int RESPONSE_OK=200;
    public static final MediaType RETROFIT_JSON = MediaType.parse("application/json; charset=utf-8");

    /*
    * 下面API列表 :
    * */
    /*
    * @descrption
    * 通過post獲取天氣數據
    * @params
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
        return getWeatherAPI().getBeanBodyWeather(body)
                .compose(this.<HttpWeatherBean>applySchedulers());

    }

    /*
    *
    * */
    public Observable<HttpWeatherBean> getBeanWeather(String format, String cityname, String dtype, String key){

        return getWeatherAPI().getBeanWeather(format,cityname,dtype,key)
                .compose(this.<HttpWeatherBean>applySchedulers());

    }


    /* **************************************************************************************************************
    * 下面接口請求返回成功與失敗的分離
    * ***************************************************************************************************************/
    protected <T> Observable.Transformer<BaseWeatherBean, T> applySchedulers() {
        return (Observable.Transformer<BaseWeatherBean, T>) transformer;
    }


    final Observable.Transformer transformer = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(new Func1<BaseWeatherBean,Object>() {
                        @Override
                        public Object call(BaseWeatherBean response) {
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
    public <T> Observable<Object> flatResponse(final BaseWeatherBean response) {
        return Observable.create(new Observable.OnSubscribe<Object>() {

            @Override
            public void call(Subscriber<? super Object> subscriber) {
                /*
                * 这个地方分离请求成功或失败
                * 必须是HttpWeatherBeanT基类
                * */
                if (response.getError_code()==RESPONSE_OK) {
                    if (!subscriber.isUnsubscribed()) {
                        /*
                        * 分发给观察者
                        * */
                        subscriber.onNext(response);
                    }
                } else {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onError(new NetException(response.getError_code(), response.getReason()));
                    }
                    return;
                }

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }

                /*
                * 如果這裡不對http請求返回的做預處理,那就直接执行下面一条,让UI层获取失败信息做具体分析
                * subscriber.onNext(response);
                * */

            }
        });
    }
}
