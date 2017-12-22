package com.panda.org.highwrapper.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panda.org.highwrapper.http.api.WeatherAPI;
import com.panda.org.highwrapper.ui.app.base.BaseActivity;

/**
 * Created by rd0348 on 2017/12/21 0021.
 * 这个基类涉及到布局设置,个人并不推荐这种做法,虽然子类都要用到布局
 */

public abstract class BaseFragment extends Fragment {

    public BaseActivity mActivity;
    public View mRootView;
    protected WeatherAPI api=new WeatherAPI();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView =inflater.inflate(getContentViewId(),container,false);
        initView(savedInstanceState);

        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract void initView(Bundle savedInstanceState);
    public abstract int getContentViewId();

}
