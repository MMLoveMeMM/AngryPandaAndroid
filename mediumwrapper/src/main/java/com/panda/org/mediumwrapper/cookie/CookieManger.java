package com.panda.org.mediumwrapper.cookie;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by rd0348 on 2017/12/20 0020.
 */

public class CookieManger implements CookieJar {
    private static final String TAG = "NovateCookieManger";
    private static Context mContext;
    //private static PersistentCookieStore cookieStore;
    /**
     * Mandatory constructor for the NovateCookieManger
     */
    public CookieManger(Context context) {
        mContext = context;
        /*if (cookieStore == null) {
            cookieStore = new PersistentCookieStore(mContext);
        }*/
    }
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                //cookieStore.add(url, item);
            }
        }
    }
    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies =null;//= cookieStore.get(url);
        return cookies;
    }
}
