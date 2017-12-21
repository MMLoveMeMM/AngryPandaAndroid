package com.panda.org.highwrapper.ui.fragment;

import android.os.Bundle;

import com.panda.org.highwrapper.R;
import com.panda.org.highwrapper.ui.fragment.base.BaseFragment;


/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public class UserInfoFragment extends BaseFragment {

    public static UserInfoFragment newInstance() {
        return new UserInfoFragment();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_user;
    }


}
