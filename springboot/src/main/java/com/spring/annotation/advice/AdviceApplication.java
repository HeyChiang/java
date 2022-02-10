package com.spring.annotation.advice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ControllerAdvice 的三种始终方法测试类
 */
@SpringBootApplication
public class AdviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdviceApplication.class,args);
    }
}
