package com.panda.org.highwrapper.http.interceptor;

import android.content.Context;

import okhttp3.Cache;

/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public class CacheProvide {
    Context mContext;

    public CacheProvide(Context context) {
        mContext = context;
    }

    public Cache provideCache() {//使用应用缓存文件路径，缓存大小为10MB
        return new Cache(mContext.getCacheDir(), 10240 * 1024);
    }
}
