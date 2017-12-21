package com.panda.org.angrypandaandroidstructure.rx;

import com.panda.org.angrypandaandroidstructure.action.VmBaseAction;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rd0348 on 2017/12/18 0018.
 */

public class RxObservable {

    public static <T> Observable<T> CreateIoObservable(Observable.OnSubscribe<T> f){

        return Observable.create(f)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


    public static abstract class HttpSubscriber<T> extends Subscriber<T>{

        @Override
        public void onCompleted() {

        }

        @Override
        public void onNext(T t) {
            onComplete(t);
        }

        protected abstract void onComplete(T t);
    }

}
