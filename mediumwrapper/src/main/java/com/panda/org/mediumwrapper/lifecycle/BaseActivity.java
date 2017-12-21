package com.panda.org.mediumwrapper.lifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.panda.org.mediumwrapper.R;
import com.panda.org.mediumwrapper.lifecycle.http.retrofit.exp.APIException;
import com.panda.org.mediumwrapper.lifecycle.ui.DialogLoading;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class BaseActivity extends FragmentActivity {

    private DialogLoading loading;
    protected Activity activity;
    protected Toast mToast = null;

    public CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompositeSubscription=new CompositeSubscription();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }


    public interface OnCompletedListenter {
        void onCompleted();

        void onError();
    }
    public <T> Subscriber newSubscriber(final Action1<? super T> onNext, final OnCompletedListenter listenter) {
        return new Subscriber<T>() {

            @Override
            public void onCompleted() {
                //hideLoadingDialog();
                if (listenter != null) {
                    listenter.onCompleted();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof APIException) {
                    APIException exception = (APIException) e;
                    showToast(exception.message);
                } else if (e instanceof SocketTimeoutException) {
                    //showToast(e.getMessage());
                } else if (e instanceof ConnectException) {
                    //showToast(e.getMessage());
                }
                //Logutil.e(TAG, String.valueOf(e.getMessage()), e);
                if (listenter != null) {
                    //listenter.onError();
                }
                //hideLoadingDialog();
            }

            @Override
            public void onNext(T t) {
                if (!mCompositeSubscription.isUnsubscribed()) {
                    /*
                    * 这个地方加一个错误码判断
                    * */
                    onNext.call(t);
                }
            }

        };
    }

    /*
    *
    * */
    public void showToast(String content) {
        if (mToast == null) {
            mToast = Toast.makeText(this, content, Toast.LENGTH_SHORT);
        } else {
            if (!TextUtils.isEmpty(content)) {
                mToast.setText(content);
            } else {
                mToast.setText("network error"/*getString(R.string.api_error)*/);
                mToast.show();
            }
        }
        mToast.show();
    }

    public void showLoadingDialog() {
        if (loading == null) {
            loading = new DialogLoading(this);
        }
        loading.show();
    }

    protected void hideLoadingDialog() {
        if (loading != null) {
            loading.dismiss();
        }

    }

}
