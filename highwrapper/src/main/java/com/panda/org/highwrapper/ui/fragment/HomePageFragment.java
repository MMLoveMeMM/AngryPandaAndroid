package com.panda.org.highwrapper.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.panda.org.highwrapper.R;
import com.panda.org.highwrapper.bean.weather.HttpWeatherBean;
import com.panda.org.highwrapper.databinding.FragmentMainhomeBinding;
import com.panda.org.highwrapper.http.api.WeatherAPI;
import com.panda.org.highwrapper.ui.BaseActivity;
import com.panda.org.highwrapper.ui.fragment.home.HomeOneFragment;
import com.panda.org.highwrapper.ui.fragment.home.HomeThreeFragment;
import com.panda.org.highwrapper.ui.fragment.home.HomeTwoFragment;
import com.panda.org.highwrapper.ui.widget.TabPageAdapter;

import java.util.ArrayList;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public class HomePageFragment extends Fragment {

    public static HomePageFragment newInstance() {
        return new HomePageFragment();
    }
    protected WeatherAPI api=new WeatherAPI();
    private FragmentMainhomeBinding mBinding;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private HomeOneFragment mHomeOneFragment;
    private HomeTwoFragment mHomeTwoFragment;
    private HomeThreeFragment mHomeThreeFragment;

    private ImageView mHttpImage;

    protected BaseActivity mActivity;

    //@Override
    protected void initView(Bundle savedInstanceState) {

        initFragment();

        mBinding.titleBar.topFilterMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subscription subscription=api.getBeanWeather("2","深圳","json","3a8c66214031c01135f509434ae44b84")
                        .subscribe(mActivity.newSubscriber(new Action1<HttpWeatherBean>() {
                            @Override
                            public void call(HttpWeatherBean bean) {
                                Log.d("okhttp", "************* bean : "+bean.getReason());
                                //goNext(o);
                            }
                        }, null));
                mActivity.mCompositeSubscription.add(subscription);//统一管理回撤,销毁
            }
        });

    }

    /*
    * 覆盖父类
    * */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mainhome, container,
                false);
        //initData(savedInstanceState);
        initView(savedInstanceState);
        return mBinding.getRoot();
    }

    private void initFragment(){

        ArrayList<String> tabtitles = new ArrayList<>();
        tabtitles.add("home1");
        tabtitles.add("home2");
        tabtitles.add("home3");

        mHomeOneFragment = HomeOneFragment.newInstance();
        mHomeTwoFragment = HomeTwoFragment.newInstance();
        mHomeThreeFragment = HomeThreeFragment.newInstance();

        mFragments.add(mHomeOneFragment);
        mFragments.add(mHomeTwoFragment);
        mFragments.add(mHomeThreeFragment);

        TabPageAdapter pagerAdapter = new TabPageAdapter(getChildFragmentManager(), mFragments,
                tabtitles);

        mBinding.pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mBinding.pager.setAdapter(pagerAdapter);
        mBinding.pager.setOffscreenPageLimit(mFragments.size());
        mBinding.pager.setCurrentItem(0);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }


}
