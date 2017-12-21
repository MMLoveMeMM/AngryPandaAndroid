package com.panda.org.highwrapper.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.panda.org.highwrapper.R;
import com.panda.org.highwrapper.ui.BaseActivity;
import com.panda.org.highwrapper.ui.fragment.HomePageFragment;
import com.panda.org.highwrapper.ui.fragment.SettingFragment;
import com.panda.org.highwrapper.ui.fragment.UserInfoFragment;

public class HomeActivity extends BaseActivity {

    Fragment mHomePageFragment;
    Fragment mUserInfoFragment;
    Fragment mSettingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initFragment() {
        mHomePageFragment = HomePageFragment.newInstance();
        mUserInfoFragment = UserInfoFragment.newInstance();
        mSettingFragment = SettingFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mHomePageFragment, mHomePageFragment.getClass().getName())
                .add(R.id.fragment_container, mUserInfoFragment, mUserInfoFragment.getClass().getName())
                .add(R.id.fragment_container, mSettingFragment, mSettingFragment.getClass().getName())
                .show(mHomePageFragment)
                .hide(mUserInfoFragment)
                .hide(mSettingFragment)
                .commit();
    }

    @Override
    public void showFragment() {
        getSupportFragmentManager().beginTransaction()
                .show(mHomePageFragment)
                .hide(mUserInfoFragment)
                .commit();
        //setCurrentPage(1);
    }

    @Override
    public void hideFragment() {
        getSupportFragmentManager().beginTransaction()
                .show(mUserInfoFragment)
                .hide(mHomePageFragment)
                .commit();
        //setCurrentPage(2);
    }


}
