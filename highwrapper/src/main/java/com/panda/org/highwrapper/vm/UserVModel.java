package com.panda.org.highwrapper.vm;

import android.util.Log;
import android.view.View;

import com.panda.org.highwrapper.bean.weather.HttpWeatherBean;
import com.panda.org.highwrapper.datas.UserDatas;
import com.panda.org.highwrapper.http.api.WeatherAPI;
import com.panda.org.highwrapper.ui.app.base.BaseActivity;
import com.panda.org.highwrapper.ui.fragment.UserInfoFragment;
import com.panda.org.highwrapper.ui.fragment.base.BaseFragment;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by rd0348 on 2017/12/22 0022.
 * 数据请求和数据更新都在这里完成
 */

public class UserVModel {

    private UserInfoFragment mFragment;
    public String httpresponse;
    private UserDatas mUserDatas;

    protected WeatherAPI api=new WeatherAPI();

    public UserVModel(UserInfoFragment user){

        mFragment= (UserInfoFragment) user;
        mUserDatas=new UserDatas("hello world");
        httpresponse=mUserDatas.getHttpresponse();

    }

    public void OnQueryInfo(View view){

        Log.i("okhttp","onQueryInfo ...");
        Subscription subscription=api.getBeanWeather("2","深圳","json","3a8c66214031c01135f509434ae44b84")
                .subscribe(mFragment.mActivity.newSubscriber(new Action1<HttpWeatherBean>() {
                    @Override
                    public void call(HttpWeatherBean bean) {
                        updateText(bean);
                    }
                }, null));
        mFragment.mActivity.mCompositeSubscription.add(subscription);//统一管理回撤,销毁
    }

    public void updateText(HttpWeatherBean bean){

        httpresponse=bean.getResult().getToday().getCity();
        Log.i("okhttp","city : "+httpresponse);
        mUserDatas.setHttpresponse(httpresponse);
        //mFragment.getBinding().text.setText(httpresponse);
        mFragment.getBinding().invalidateAll();//记得更新数据后,执行这一条语句刷新数据到UI上

    }


}
