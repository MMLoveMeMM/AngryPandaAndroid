package com.panda.org.highwrapper.cache;

import com.panda.org.highwrapper.bean.weather.HttpWeatherBean;
import com.panda.org.highwrapper.cache.base.BaseCacheList;
import com.panda.org.highwrapper.cache.base.CacheInterface;

/**
 * Created by rd0348 on 2017/12/22 0022.
 */

public class WeatherCache extends BaseCacheList<HttpWeatherBean> implements CacheInterface<HttpWeatherBean> {

    @Override
    public void saveOrUpdate(HttpWeatherBean httpWeatherBean, boolean enable) {
        super.cacheSaveOrUpdate(httpWeatherBean); // 这个是内存缓存
        if(enable) {
            // 同时也可以增加保存到sharepreference等地方
        }
    }

    @Override
    public void remove(String key, boolean enable) {
        super.cacheRemove(key);
    }

    @Override
    public void loadCache() {

        /*
        * 可以从网络上获取数据下来
        * 也可以从数据中读取数据到内存中
        * 获得数据以后保存更新到缓存
        * */
        cacheSaveOrUpdate(null /*HttpWeatherBean*/);

    }

    @Override
    protected String getObjectKey(HttpWeatherBean bean) {
        return String.valueOf("这里要从bean抽出一个独一无二的字段作为key值保存到map中"/*bean.getError_code()*/);
    }

}
