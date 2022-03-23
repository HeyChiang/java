package com.chiang.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @author Chiang
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @GetMapping("/test")
    public String getTest(){
        return "Hello!";
    }


    @GetMapping("/set")
    public String set(String key,String value){
        redisTemplate.opsForValue().set(key,new Student());
        return "ok";
    }

    @GetMapping("/get")
    public String get(String key){
        redisTemplate.opsForHash();
        Student s = (Student) redisTemplate.opsForValue().get(key);
        log.info("获得：{}",s);
        return s.name;
    }

    @Data
    @NoArgsConstructor
    public static class Student implements Serializable {
        String name ="张三";
        Integer age = 80;
    }

}
