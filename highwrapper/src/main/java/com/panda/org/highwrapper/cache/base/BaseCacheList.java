package com.panda.org.highwrapper.cache.base;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by rd0348 on 2017/12/22 0022.
 */

public abstract class BaseCacheList<E> {
    protected final Object mObject = new Object();//枷锁保证操作安全
    protected abstract String getObjectKey(E elem);
    protected LinkedHashMap<String, E> mHashMap = new LinkedHashMap<>();

    protected void cacheSaveOrUpdate(E e) {
        synchronized (mObject) {
            mHashMap.put(getObjectKey(e), e);
        }
    }

    public void cacheRemove(String key) {
        synchronized (mObject) {
            mHashMap.remove(key);
        }
    }

    public boolean containsKey(String userId) {
        synchronized (mObject) {
            return mHashMap.containsKey(userId);
        }
    }

    public E get(String key) {
        synchronized (mObject) {
            return mHashMap.get(key);
        }
    }

    public void clear() {
        synchronized (mObject) {
            mHashMap.clear();
        }
    }

    public ArrayList<E> getList() {
        synchronized (mObject) {
            ArrayList<E> list = new ArrayList<>();
            list.addAll(mHashMap.values());
            return list;
        }
    }

    public int size() {
        synchronized (mObject) {
            return mHashMap.size();
        }
    }

}
