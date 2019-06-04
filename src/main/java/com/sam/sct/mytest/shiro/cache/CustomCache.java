package com.sam.sct.mytest.shiro.cache;

import com.sam.sct.mytest.util.JWTUtile;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.Set;

/**
 * @author zhangshexin
 * @createTime 2019/5/23
 */
public class CustomCache<K,V> implements Cache<K,V>{
    /**
     * redis-key-前缀-shiro:cache:
     */
    public final static String PREFIX_SHIRO_CACHE="shiro:cache:";

    /**
     * 过期时间-5分钟
     */
    private static final int EXPIRE_TIME=5* 60*1000;



//    private String getKey(Object key){
//        return PREFIX_SHIRO_CACHE+ JWTUtile.getu
//    }


    @Override
    public Object get(Object o) throws CacheException {
        return null;
    }

    @Override
    public Object put(Object o, Object o2) throws CacheException {
        return null;
    }

    @Override
    public Object remove(Object o) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set keys() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }
}
