package com.panda.org.angrypandaandroidstructure.action;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by rd0348 on 2017/12/18 0018.
 */

public abstract class VmBaseAction<T> implements Observable.OnSubscribe<T> {

    protected abstract int CreateHttpRequest();

    @Override
    public void call(Subscriber<? super T> subscriber) {
        int handlecode=CreateHttpRequest();
    }



}
