package com.spring.intercept;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 先开始执行过滤器，然后执行拦截器。
 * @author Chiang
 */
@SpringBootApplication
public class Example {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Example.class, args);
//        run.close();
    }

}
