package com.panda.org.mediumwrapper.lifecycle.http.retrofit.transformer;

import com.panda.org.mediumwrapper.bean.HttpWeatherBean;
import com.panda.org.mediumwrapper.lifecycle.http.retrofit.exp.APIException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public class TransformerUtils {

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
    public <T> Observable<Object> flatResponse(final HttpWeatherBean response) {
        return Observable.create(new Observable.OnSubscribe<Object>() {

            @Override
            public void call(Subscriber<? super Object> subscriber) {
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
