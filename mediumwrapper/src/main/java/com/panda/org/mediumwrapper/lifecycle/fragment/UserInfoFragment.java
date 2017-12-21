package com.panda.org.mediumwrapper.lifecycle.fragment;

import android.os.Bundle;

import com.panda.org.mediumwrapper.R;

/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public class UserInfoFragment extends BaseFragment {

    public static UserInfoFragment newInstance() {
        return new UserInfoFragment();
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {

    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_user;
    }


}
