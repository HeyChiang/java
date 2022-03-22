package com.chiang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chiang
 */
@RestController
public class TestController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("/test")
    public String getTest(){
        return "Hello!";
    }


    @GetMapping("/set")
    public String set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
        return "ok";
    }

    @GetMapping("/get")
    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
