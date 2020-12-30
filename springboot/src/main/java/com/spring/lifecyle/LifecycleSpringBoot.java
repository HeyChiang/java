package com.spring.lifecyle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring bean的生命周期测试类
 */
@RestController
@SpringBootApplication
public class LifecycleSpringBoot {

    final MySpringBean mySpringBean;

    public LifecycleSpringBoot(MySpringBean mySpringBean) {
        this.mySpringBean = mySpringBean;
    }

    @RequestMapping("/")
    String home() {
        System.out.println(mySpringBean);
        return "Hello!";
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(LifecycleSpringBoot.class, args);
//        run.close();
    }

}