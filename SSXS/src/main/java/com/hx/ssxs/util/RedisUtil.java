package com.hx.ssxs.util;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis工具类
 * @author Jerome Guo
 * @date 2021/03/02
 * */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 字符串类型写入操作
     * @param key key值
     * @param value value值
     */
    public void setString(String key, String value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 可设置过期时间字符串类型写入操作
     * @param key key值
     * @param value value值
     * @param expire 过期时间
     * @param timeUnit 过期时间单位
     */
    public void setLock(String key, String value, Long expire, TimeUnit timeUnit) {
        this.redisTemplate.opsForValue().set(key, value, expire, timeUnit);
    }

    /**
     * 字符串类型读取操作
     * @param key key值
     * @return value值
     */
    public String getString(String key) {
        return (String) this.redisTemplate.opsForValue().get(key);
    }
    
    /**
     * hash类型写入操作
     * @param key key值
     * @param field field值
     * @param value value值
     */
    public void setHash(String key, String field, Object value) {
        this.redisTemplate.opsForHash().put(key, field, value);
    }
    
    /**
     * hash类型读取操作
     * @param key key值
     * @param field field值
     * @return value值
     */
    public Object getHash(String key,String field) {
        return  this.redisTemplate.opsForHash().get(key, field);
    }
    
    /**
     * hash类型删除操作
     * @param key key值
     * @param field field值
     * @return value值
     */
    public void deletehash(String key,String field) {
        this.redisTemplate.opsForHash().delete(key, field);
    }
}
