package com.spring.annotation.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动测试类
 */
@SpringBootApplication
public class AOPApplication implements CommandLineRunner {

    final Boy boy;

    final Girl girl;

    public AOPApplication(Boy boy, Girl girl) {
        this.boy = boy;
        this.girl = girl;
    }

    public static void main(String[] args) {
        SpringApplication.run(AOPApplication.class,args).close();
    }

    @Override
    public void run(String... args) throws Exception {
        boy.buy();
        girl.buy();
    }
}
