package com.panda.org.mediumwrapper.lifecycle;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.panda.org.mediumwrapper.R;
import com.panda.org.mediumwrapper.bean.HttpWeatherBean;
import com.panda.org.mediumwrapper.lifecycle.fragment.HomePageFragment;
import com.panda.org.mediumwrapper.lifecycle.fragment.SettingFragment;
import com.panda.org.mediumwrapper.lifecycle.fragment.UserInfoFragment;
import com.panda.org.mediumwrapper.lifecycle.http.retrofit.api.HttpAPI;

import rx.Subscription;
import rx.functions.Action1;
import rx.internal.util.ActionSubscriber;
import rx.observers.SafeSubscriber;

public class LifeRecyleActivity extends BaseActivity {

    private ImageView mHomeSel;
    private ImageView mUserSel;

    Fragment mHomePageFragment;
    Fragment mUserInfoFragment;
    Fragment mSettingFragment;

    private HttpAPI api=new HttpAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_recyle);

        initFragments();
        initView();

    }

    public void initView(){

        mHomeSel=(ImageView)findViewById(R.id.homesel);
        mHomeSel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHomePage();

                Subscription subscription=api.getBeanBodyWeather("2","深圳","json","3a8c66214031c01135f509434ae44b84")
                .subscribe(newSubscriber(new Action1<HttpWeatherBean>() {
                    @Override
                    public void call(HttpWeatherBean bean) {
                        Log.d("okhttp", "************* bean : "+bean.getReason());
                        //goNext(o);
                    }
                }, null));
                mCompositeSubscription.add(subscription);//统一管理回撤,销毁

            }
        });

        mUserSel=(ImageView)findViewById(R.id.usersel);
        mUserSel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUserPage();
            }
        });
    }

    void initFragments() {
        mHomePageFragment = HomePageFragment.newInstance();
        mUserInfoFragment = UserInfoFragment.newInstance();
        mSettingFragment = SettingFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mHomePageFragment, mHomePageFragment.getClass().getName())
                .add(R.id.fragment_container, mUserInfoFragment, mUserInfoFragment.getClass().getName())
                .add(R.id.fragment_container, mSettingFragment, mSettingFragment.getClass().getName())
                .show(mSettingFragment)
                .hide(mUserInfoFragment)
                .hide(mHomePageFragment)
                .commit();
    }

    void showHomePage() {
        getSupportFragmentManager().beginTransaction()
                .show(mHomePageFragment)
                .hide(mUserInfoFragment)
                .commit();
        //setCurrentPage(1);
    }

    void showUserPage() {
        getSupportFragmentManager().beginTransaction()
                .show(mUserInfoFragment)
                .hide(mHomePageFragment)
                .commit();
        //setCurrentPage(2);
    }

}
