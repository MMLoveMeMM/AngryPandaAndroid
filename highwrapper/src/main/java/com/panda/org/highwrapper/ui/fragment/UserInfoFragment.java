package com.panda.org.highwrapper.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panda.org.highwrapper.R;
import com.panda.org.highwrapper.databinding.FragmentMainuserBinding;
import com.panda.org.highwrapper.datas.UserDatas;
import com.panda.org.highwrapper.ui.app.base.BaseActivity;
import com.panda.org.highwrapper.ui.fragment.base.BaseFragment;
import com.panda.org.highwrapper.ui.fragment.base.VMBaseFragment;
import com.panda.org.highwrapper.vm.UserVModel;


/**
 * Created by rd0348 on 2017/12/20 0020.
 * 布局,数据绑定,页面的声明周期都在这里完成管理
 */

public class UserInfoFragment extends VMBaseFragment<FragmentMainuserBinding> {

    public static UserInfoFragment newInstance() {
        return new UserInfoFragment();
    }

    private UserVModel mUserVModel;
    protected FragmentMainuserBinding mBinding;

    protected void initView(Bundle savedInstanceState) {

        mUserVModel=new UserVModel(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mainuser, container,
                false);
        initView(savedInstanceState);
        mBinding.setUser(mUserVModel);
        return mBinding.getRoot();
    }

    public FragmentMainuserBinding getBinding(){
        return mBinding;
    }

}
