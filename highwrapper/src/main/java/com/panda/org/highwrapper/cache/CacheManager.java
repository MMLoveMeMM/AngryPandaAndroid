package com.panda.org.highwrapper.cache;

/**
 * Created by rd0348 on 2017/12/22 0022.
 * 同意由单实例进行内存管理操作
 */

public class CacheManager {

    private WeatherCache mWeatherCache;

    private static CacheManager sInstance = new CacheManager();
    public static CacheManager getInstance() {
        return sInstance;
    }

    public CacheManager(){
        mWeatherCache=new WeatherCache();
    }

    public void loadCache() {
        mWeatherCache.loadCache();
    }

    public WeatherCache getWeatherCache(){
        return mWeatherCache;
    }


}
