package com.panda.org.highwrapper.ui.fragment.home;

import android.os.Bundle;

import com.panda.org.highwrapper.R;
import com.panda.org.highwrapper.ui.fragment.base.BaseFragment;

/**
 * Created by rd0348 on 2017/12/21 0021.
 */

public class HomeThreeFragment extends BaseFragment {

    public static HomeThreeFragment newInstance() {
        return new HomeThreeFragment();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_three;
    }

}
