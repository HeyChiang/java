package com.example.demo.config;

import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedissonController {

    private final RedissonClient redissonClient;
    private final RedisTemplate<String,String> redisTemplate;

    public RedissonController(RedissonClient redissonClient, RedisTemplate<String, String> redisTemplate) {
        this.redissonClient = redissonClient;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping(value = "/redisson/{key}")
    public String redissonTest(@PathVariable("key") String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            lock.lock();
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return "已解锁";
    }

    @PostMapping(value = "/put")
    public String setRedisKeyValue(String key,String value){
        System.out.println("add key:"+key+"and value:"+value);

        RAtomicLong set = redissonClient.getAtomicLong(key);
        long getValue = set.addAndGet(Long.parseLong(value));
        System.out.println("getValue："+getValue);

        RKeys keys = redissonClient.getKeys();
        System.out.println(keys.count());
        for (String s : keys.getKeys()) {
            System.out.println("key："+s );
        }

        return "success";
    }
}