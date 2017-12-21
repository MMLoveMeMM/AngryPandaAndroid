package com.panda.org.mediumwrapper.lifecycle.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.panda.org.mediumwrapper.R;
import com.panda.org.mediumwrapper.lifecycle.BaseActivity;

/**
 * Created by rd0348 on 2017/12/21 0021.
 */

public class SettingFragment extends BaseFragment {

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    private Fragment mHomePageFragment;
    private Fragment mUserInfoFragment;

    private ImageView imagesel1;
    private ImageView imagesel2;

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        initFragments();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_setting;
    }

    void initView(){

        imagesel1=(ImageView)mRootView.findViewById(R.id.homesel);
        imagesel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHomePage();
            }
        });
        imagesel2=(ImageView)mRootView.findViewById(R.id.usersel);
        imagesel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUserPage();
            }
        });
    }

    void initFragments() {

        mHomePageFragment = HomePageFragment.newInstance();
        mUserInfoFragment = UserInfoFragment.newInstance();

        mActivity.getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mHomePageFragment, mHomePageFragment.getClass().getName())
                .add(R.id.fragment_container, mUserInfoFragment, mUserInfoFragment.getClass().getName())
                .show(mHomePageFragment)
                .hide(mUserInfoFragment)
                .commit();
    }

    void showHomePage() {
        mActivity.getSupportFragmentManager().beginTransaction()
                .show(mHomePageFragment)
                .hide(mUserInfoFragment)
                .commit();
        //setCurrentPage(1);
    }

    void showUserPage() {
        mActivity.getSupportFragmentManager().beginTransaction()
                .show(mUserInfoFragment)
                .hide(mHomePageFragment)
                .commit();
        //setCurrentPage(2);
    }

}
