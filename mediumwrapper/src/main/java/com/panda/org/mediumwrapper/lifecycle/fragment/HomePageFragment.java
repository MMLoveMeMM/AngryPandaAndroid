package com.panda.org.mediumwrapper.lifecycle.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.panda.org.mediumwrapper.R;
import com.panda.org.mediumwrapper.bean.HttpWeatherBean;
import com.panda.org.mediumwrapper.lifecycle.BaseActivity;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public class HomePageFragment extends BaseFragment {

    private ImageView imageView;
    private Button mImageBtn;

    public static HomePageFragment newInstance() {
        return new HomePageFragment();
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {

        imageView=(ImageView)mRootView.findViewById(R.id.image);

        mImageBtn=(Button)mRootView.findViewById(R.id.button);
        mImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Subscription subscription=api.getBeanWeather("2","深圳","json","3a8c66214031c01135f509434ae44b84")
                        .subscribe(mActivity.newSubscriber(new Action1<HttpWeatherBean>() {
                            @Override
                            public void call(HttpWeatherBean o) {
                                //Logutil.d(TAG, "-- login " + "Success");
                                //goNext(o);
                            }
                        }, null));

                mActivity.mCompositeSubscription.add(subscription);//统一管理回撤,销毁
            }
        });

    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_home;
    }


}
