package com.panda.org.highwrapper.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.panda.org.highwrapper.ui.app.base.BaseActivity;

/**
 * Created by rd0348 on 2017/12/22 0022.
 */

public abstract class VMBaseFragment<T> extends Fragment {

    public BaseActivity mActivity;
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract void initView(Bundle savedInstanceState);
    protected abstract T getBinding();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

}
