package com.chiang.spring.lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

@RestController
@SpringBootApplication
public class LifecycleSpringBoot {

    final MyTestBean myTestBean;

    public LifecycleSpringBoot(MyTestBean myTestBean) {
        this.myTestBean = myTestBean;
    }

    @RequestMapping("/")
    String home() {
        System.out.println(myTestBean);
        return "Hello!";
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(LifecycleSpringBoot.class, args);
        run.close();
    }

}