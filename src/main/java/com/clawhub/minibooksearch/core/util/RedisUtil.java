package com.clawhub.minibooksearch.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: create by lhy
 * @version: v1.0
 * @description: com.clawhub.minibooksearch.core.util
 * @date:2018/11/5
 */
@Component
public class RedisUtil {

    private final String GETREDISERROR = "获取缓存数据异常";
    private final String SETREDISERROR = "存放缓存数据异常";

    /**
     * redis 读取内容的template
     *
     * @param connectionFactory the connection factory
     * @return the string redis template
     */

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * set String key String field String value
     *
     * @param key
     * @param field
     * @param value
     * @param timeOut
     * @throws Exception
     */
    public void set(String key, String field, String value, long timeOut) throws Exception {
        try {
            RedisSerializer stringSerializer = new StringRedisSerializer();
            stringRedisTemplate.setKeySerializer(stringSerializer);
            stringRedisTemplate.opsForHash().put(key, field, value);
            stringRedisTemplate.expire(key, timeOut, TimeUnit.MINUTES);
        } catch (Exception e) {
            throw new Exception(SETREDISERROR, e);
        }
    }

    /**
     * set String key String field Object value
     *
     * @param key
     * @param field
     * @param value
     * @param timeOut
     * @throws Exception
     */
    public void set(String key, String field, Object value, long timeOut) throws Exception {
        try {
            stringRedisTemplate.opsForHash().put(key, field, value);
            stringRedisTemplate.expire(key, timeOut, TimeUnit.MINUTES);
        } catch (Exception e) {
            throw new Exception(SETREDISERROR, e);
        }
    }

    /**
     * set String key String value
     *
     * @param key
     * @param value
     * @param timeOut
     * @throws Exception
     */
    public void set(String key, String value, long timeOut) throws Exception {
        try {
            RedisSerializer stringSerializer = new StringRedisSerializer();
            stringRedisTemplate.setKeySerializer(stringSerializer);
            stringRedisTemplate.opsForValue().append(key, value);
            stringRedisTemplate.expire(key, timeOut, TimeUnit.MINUTES);
        } catch (Exception e) {
            throw new Exception(SETREDISERROR, e);
        }
    }

    /**
     * Set.
     *
     * @param key the key
     * @throws Exception the exception
     */
    public void set(String key) throws Exception {
        try {
            RedisSerializer stringSerializer = new StringRedisSerializer();
            stringRedisTemplate.setKeySerializer(stringSerializer);
            stringRedisTemplate.opsForValue().append(key, "1");
        } catch (Exception e) {
            throw new Exception(SETREDISERROR, e);
        }
    }

    /**
     * get String key
     *
     * @param key
     * @return String
     * @throws Exception
     */
    public String getString(String key) throws Exception {
        try {
            return stringRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            throw new Exception(GETREDISERROR, e);
        }
    }

    /**
     * get String key String field
     *
     * @param key
     * @param field
     * @return String
     * @throws Exception
     */
    public String getString(String key, String field) throws Exception {
        try {
            return stringRedisTemplate.opsForHash().get(key, field).toString();
        } catch (Exception e) {
            throw new Exception(GETREDISERROR, e);
        }
    }

    /**
     * get String key String field
     *
     * @param key
     * @param field
     * @return Object
     * @throws Exception
     */
    public Object getObject(String key, String field) throws Exception {
        try {
            return stringRedisTemplate.opsForHash().get(key, field);
        } catch (Exception e) {
            throw new Exception(GETREDISERROR, e);
        }
    }

    /**
     * remove String key
     *
     * @param key
     */
    public void remove(String key) {
        stringRedisTemplate.expire(key, 1, TimeUnit.NANOSECONDS);
    }

}
