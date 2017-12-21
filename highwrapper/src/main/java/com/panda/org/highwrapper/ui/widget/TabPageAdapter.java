package com.panda.org.highwrapper.ui.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Created by rd0348 on 2017/12/21 0021.
 */

public class TabPageAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<Fragment> mFragments;
    private final ArrayList<String> mTitles;

    public TabPageAdapter(FragmentManager fm, ArrayList<Fragment> fragments,
                            ArrayList<String> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
        if(mFragments.size()!=mTitles.size()){
            throw new InvalidParameterException("fragments size not equal to titles size");
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
