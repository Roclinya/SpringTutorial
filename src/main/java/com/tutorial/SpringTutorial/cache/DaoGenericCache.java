package com.tutorial.SpringTutorial.cache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Component
public class DaoGenericCache  implements IDaoGenericCache<String, Object> {
    private static final Long DEFAULT_CACHE_TIMEOUT = 120000L; // 預設快取存活時間 120sec

    public final static long BUFFER_INTERVAL = 6000L; // 預設快取緩衝時間 6sec

    protected Long cacheTimeout;

    protected Map<String, CacheValue> cacheMap;

    private Object daoGenericCacheLock = new Object();
    protected interface CacheValue {
        Object getValue();

        long getCreatedAt();

        long getUpdateTime();

        void setUpdateTime(long now);

    }


    public DaoGenericCache() {
        this(DEFAULT_CACHE_TIMEOUT, null);
    }
    //無參數建構子,初始化後執行
    public DaoGenericCache(Long cacheTimeout, Consumer<String> traceLogger) {

        this.cacheTimeout = cacheTimeout;
        //清除 cacheMap, 目的：初始化剛啟動時reset 存放cache的map
        this.clear();

        // 處理超過 DEFAULT_CACHE_TIMEOUT(預設120sec) 的舊快取資料
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        // 休息1秒，避免CPU飆高
						Thread.sleep(1000);
                        synchronized (daoGenericCacheLock) {
                            daoGenericCacheLock.wait(1000);
                        }
                        clean();
                    } catch (InterruptedException e) {
                        if (traceLogger != null) {
                            System.out.println("記錄 logStackTrace ");
//                            traceLogger.accept(StackTraceUtil.logStackTrace(e));
                        }
                    }
                }
            }
        }.start();

    }


    @Override
    public void clear() {
        if (this.cacheMap != null) {
            this.cacheMap.clear();
        }
        this.cacheMap = new ConcurrentHashMap<>();
    }

    @Override
    public void clean() {

    }

    @Override
    public void remove(String key) {

    }

    @Override
    public boolean containsKey(String key) {
        return false;
    }

    @Override
    public void put(String key, Object value) {
        IDaoGenericCache.super.put(key, value);
    }

    public Map<String, CacheValue> getCacheMap() {
        return this.cacheMap;
    }

    @Override
    public Optional<Object> get(String key) {
        return Optional.empty();
    }

    @Override
    public void put(String key, Object value, CacheValueAdapter adapter) {

    }
}
