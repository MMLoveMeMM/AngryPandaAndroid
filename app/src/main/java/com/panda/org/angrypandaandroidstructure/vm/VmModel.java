package com.panda.org.angrypandaandroidstructure.vm;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.panda.org.angrypandaandroidstructure.MainActivity;
import com.panda.org.angrypandaandroidstructure.action.VmAction;
import com.panda.org.angrypandaandroidstructure.action.VmBaseAction;
import com.panda.org.angrypandaandroidstructure.data.Panda;
import com.panda.org.angrypandaandroidstructure.rx.RxObservable;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by rd0348 on 2017/12/18 0018.
 */

public class VmModel {

    private Panda panda;
    private MainActivity mActivity;
    Subscriber<String> msubscrib;
    public String pandaname;

    public VmModel(MainActivity activity,Panda panda)
    {
        this.mActivity=activity;
        this.panda=panda;
        pandaname=panda.getPandaname();
    }

    public void OnLoadImage(View view){

        pandaname="load image";
        panda.setPandaname(pandaname);
        Toast.makeText(mActivity,"load image start ...",Toast.LENGTH_SHORT).show();

        msubscrib=new Subscriber<String>() {

            @Override
            public void onCompleted() {
                Toast.makeText(mActivity,"load image finish ...",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Toast.makeText(mActivity,"load image : "+s,Toast.LENGTH_SHORT).show();
            }
        };

        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        sub.onCompleted();
                    }

                }

        );

        Subscription sub = RxObservable.CreateIoObservable(new VmAction())
        .subscribe(new RxObservable.HttpSubscriber<Panda>(){

            @Override
            public void onError(Throwable e) {

            }

            @Override
            protected void onComplete(Panda panda) {

            }
        });

        myObservable.subscribe(msubscrib);

    }

}
