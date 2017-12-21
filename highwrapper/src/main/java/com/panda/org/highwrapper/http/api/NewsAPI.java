package com.panda.org.highwrapper.http.api;

import com.panda.org.highwrapper.bean.news.BaseNewsBean;
import com.panda.org.highwrapper.bean.news.HttpNewsBean;
import com.panda.org.highwrapper.bean.news.NewsBean;
import com.panda.org.highwrapper.bean.weather.BaseWeatherBean;
import com.panda.org.highwrapper.bean.weather.HttpWeatherBean;
import com.panda.org.highwrapper.http.exp.NetException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.panda.org.highwrapper.http.RetrofitClient.getNewsAPI;
import static com.panda.org.highwrapper.http.RetrofitClient.getWeatherAPI;

/**
 * Created by rd0348 on 2017/12/21 0021.
 */

public class NewsAPI {

    public static final int RESPONSE_OK=200;
    public static final String URL="http://v.juhe.cn/";

    public Observable<HttpNewsBean> getNews(String format, String key){

        return getNewsAPI().getNews(format,key)
                .compose(this.<HttpNewsBean>applySchedulers());

    }
    /* **************************************************************************************************************
    * 下面接口請求返回成功與失敗的分離
    * ***************************************************************************************************************/
    protected <T> Observable.Transformer<BaseNewsBean, T> applySchedulers() {
        return (Observable.Transformer<BaseNewsBean, T>) transformer;
    }


    final Observable.Transformer transformer = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(new Func1<BaseNewsBean,Object>() {
                        @Override
                        public Object call(BaseNewsBean response) {
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
    public <T> Observable<Object> flatResponse(final BaseNewsBean response) {
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
