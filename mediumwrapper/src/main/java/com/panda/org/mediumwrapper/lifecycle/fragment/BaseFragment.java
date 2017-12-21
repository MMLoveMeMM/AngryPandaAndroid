package com.panda.org.mediumwrapper.lifecycle.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panda.org.mediumwrapper.lifecycle.BaseActivity;
import com.panda.org.mediumwrapper.lifecycle.http.retrofit.api.HttpAPI;

/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public abstract class BaseFragment extends Fragment {

    protected BaseActivity mActivity;
    public View mRootView;
    protected HttpAPI api=new HttpAPI();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView =inflater.inflate(getContentViewId(),container,false);
        initAllMembersView(savedInstanceState);

        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

    protected abstract void initAllMembersView(Bundle savedInstanceState);
    public abstract int getContentViewId();

}
