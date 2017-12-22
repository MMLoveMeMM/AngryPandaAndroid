package com.panda.org.highwrapper.cache.base;

/**
 * Created by rd0348 on 2017/12/22 0022.
 */

public interface CacheInterface<E> {
    void saveOrUpdate(E e, boolean enable);
    void remove(String key, boolean enable);
    void loadCache();
}
