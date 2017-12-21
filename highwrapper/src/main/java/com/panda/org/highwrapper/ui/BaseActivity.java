package com.panda.org.highwrapper.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.panda.org.highwrapper.http.exp.NetException;
import com.panda.org.highwrapper.ui.widget.DialogLoading;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity extends FragmentActivity {

    public CompositeSubscription mCompositeSubscription;

    private DialogLoading loading;
    protected Activity activity;
    protected Toast mToast = null;

    public abstract void initView();
    public abstract void initFragment();
    public abstract void showFragment();
    public abstract void hideFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_base); // 这里只用子类Activity的布局
        mCompositeSubscription=new CompositeSubscription();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);//子类调用这个方法设置布局

        initView();
        initFragment();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public interface OnNextActionListenter {
        void onCompleted();

        void onError();
    }
    public <T> Subscriber newSubscriber(final Action1<? super T> onNext, final OnNextActionListenter listenter) {
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
                if (e instanceof NetException) {
                    NetException exception = (NetException) e;
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
                    onNext.call(t);//通知观察者
                }
            }

        };
    }

    /*
    * 下面一些通用显示
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
