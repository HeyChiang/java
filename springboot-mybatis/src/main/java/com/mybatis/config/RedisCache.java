package com.mybatis.config;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * mybatis二级缓存使用redis
 * @author Chiang
 */
public class RedisCache implements Cache {

    private String id;

    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * redis expiration time
     */
    private static final long EXPIRE_TIME_IN_MINUTES = 30;

    /**
     * 接收Mybatis传来的namespace唯一标识
     */
    public RedisCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
    /**
     * Put query result to redis
     */
    @Override
    public void putObject(Object key, Object value) {
        getRedisTemplate().opsForValue().set(key, value, EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
        logger.debug("Put query result to redis");
    }
    /**
     * Get cached query result from redis
     */
    @Override
    public Object getObject(Object key) {
        logger.debug("Get cached query result from redis");
        return getRedisTemplate().opsForValue().get(key);
    }
    /**
     * Remove cached query result from redis
     *
     */
    @Override
    public Object removeObject(Object key) {
        logger.debug("Remove cached query result from redis");
        return getRedisTemplate().delete(key);
    }
    /**
     * Clears this cache instance
     */
    @Override
    public void clear() {
        getRedisTemplate().delete(getId());
        logger.debug("Clear all the cached query result from redis");
    }
    @Override
    public int getSize() {
        Long size = getRedisTemplate().boundHashOps(getId()).size();
        return size == null ? 0 : size.intValue();
    }
    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }


    private RedisTemplate<Object,Object> getRedisTemplate(){
        return SpringUtils.getBean("redisTemplate");
    }
}