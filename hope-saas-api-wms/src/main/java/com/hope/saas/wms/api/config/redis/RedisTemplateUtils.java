package com.hope.saas.wms.api.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Maduo
 * @date 2020/3/23 14:06
 */
@Slf4j
@Component
public final class RedisTemplateUtils {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @param expire
     */
    public void set(final String key, final Object value, final long expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T get(final String key, Class<T> clazz) {
        return (T) redisTemplate.boundValueOps(key).get();
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object getObj(final String key) {
        return redisTemplate.boundValueOps(key).get();
    }

    /**
     * 删除，根据key精确匹配
     *
     * @param key
     */
    public void del(final String... key) {
        redisTemplate.delete(Arrays.asList(key));
    }

    /**
     * 批量删除，根据key模糊匹配
     *
     * @param pattern
     */
    public void delpn(final String... pattern) {
        for (String kp : pattern) {
            redisTemplate.delete(redisTemplate.keys(kp + "*"));
        }
    }

    public Set<String> keys(String key) {
        return redisTemplate.keys(key + "*");
    }

    /**
     * key是否存在
     *
     * @param key
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }
}
