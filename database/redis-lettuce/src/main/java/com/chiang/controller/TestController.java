package com.chiang.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @author Chiang
 */
@Slf4j
@RestController
public class TestController {

    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate<String,Object> redisTemplate;

    public TestController(StringRedisTemplate stringRedisTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/test")
    public String getTest(){
        return "Hello!";
    }


    @GetMapping("/set_object")
    public String setObject(String key,String value){
        redisTemplate.opsForValue().set(key,new Student());
        return "ok";
    }

    @GetMapping("/get_object")
    public String getObject(String key){
        stringRedisTemplate.opsForHash();
        Student s = (Student) redisTemplate.opsForValue().get(key);
        log.info("获得：{}",s);
        return s.name;
    }

    @GetMapping("/set")
    public String setString(String key,String value){
        stringRedisTemplate.opsForValue().set(key,value);
        return "ok";
    }

    @GetMapping("/get")
    public String getString(String key){
        stringRedisTemplate.opsForHash();
        String s = stringRedisTemplate.opsForValue().get(key).toString();
        log.info("获得：{}",s);
        return s;
    }

    @Data
    @NoArgsConstructor
    public static class Student implements Serializable {
        String name ="张三";
        Integer age = 80;
    }

}
