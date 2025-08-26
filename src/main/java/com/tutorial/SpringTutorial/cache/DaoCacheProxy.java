package com.tutorial.SpringTutorial.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.el.MethodNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public abstract class DaoCacheProxy {

    private static final String NO_PARAM_KEY = "0";
    protected abstract Class<?> getDaoClass();

    protected abstract Consumer<String> getTraceLogger();
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    protected DaoGenericCache cache;

    private final CacheValueAdapter adapter = new CacheValueKryoAdapter(getClass().getName(), this::kryoRegistration);

    public DaoCacheProxy() {
        System.out.println("DaoCacheProxy constructor call ...");
//        this.adapter = new CacheValueKryoAdapter(getClass().getName(), this::kryoRegistration);
    }


    protected <R> Optional<R> getOne(String methodName, Supplier<R> supplier, Class<R> returnType, Object... params) {
        R r = get(methodName, supplier, (obj) -> {
            return returnType.cast(obj);
        }, params);
        return Optional.ofNullable(r);
    }

    private <R> R get(String methodName, Supplier<R> supplier, Function<Object, R> caster, Object... params) {
        String cacheKey = genCacheKey(getDaoClass(), methodName, params);

        // 如果有存入過 cache
        boolean hasCacheEntry = getCache().containsKey(cacheKey);
        if (hasCacheEntry) {
            Optional<Object> opt = getCache().get(cacheKey);
            // 且 cache 中有值
            if (opt.isPresent()) {
                // 就直接從 cache 拿值
                return caster.apply(opt.get());
            }
        }

        // 某一種情況是：從 cache 取出的值為空，可能是過期被清掉了，應該要重查並放回 cache
        R r = supplier.get();
        getCache().put(cacheKey, r, getCacheValueAdapter());
        return r;
    }



    /** 註冊所有需要快取的類別 */
    protected void kryoRegistration(String kryo) {
        // 由子類別自行註冊要序列化的類別
        System.out.println("kryoRegistration method call ...");
    }

    /**
     * 檢查 clazz 是否存在該 methodName (不檢查參數類型) 若符合則產生 CacheKey
     *
     * @param clazz
     * @param methodName
     * @param args
     * @return
     */
    private String genCacheKey(Class<?> clazz, String methodName, Object... args) {
        Method method = null;
        for (Method m : clazz.getMethods()) {
            if (methodName.equals(m.getName())) {
                method = m;
                break;
            }
        }
        if (method == null) {
            throw new MethodNotFoundException();
        }

        final char sp = ':';
        StringBuilder strBuilder = new StringBuilder();
        // 類別名稱
        strBuilder.append(clazz.getName());
        strBuilder.append(sp);
        // 方法名稱
        strBuilder.append(method.getName());
        strBuilder.append(sp);
        if (args.length > 0) {
            // 參數值
            String val = "";
            for (Object arg : args) {
                try {
                    val = getObjectMapper().writeValueAsString(arg);
                } catch (Exception e) {
                    val = arg.toString();
                } finally {
                    strBuilder.append(val.hashCode());
                }
            }
        } else {
            strBuilder.append(NO_PARAM_KEY);
        }

        return strBuilder.toString();
    }

    protected ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }

    protected DaoGenericCache getCache() {
//        this.cache.setTraceLogger(getTraceLogger());
        return this.cache;
    }

    protected CacheValueAdapter getCacheValueAdapter() {
        return this.adapter;
    }
}
