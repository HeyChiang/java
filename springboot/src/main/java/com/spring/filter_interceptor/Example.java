package com.spring.filter_interceptor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@RestController
@SpringBootApplication
public class Example {

    @RequestMapping("/")
    String home() {
        System.out.println("Controller be called!!");
        return "success";
    }

    @RequestMapping("/body")
    @ResponseBody
    BodyTest bodyTest(@RequestBody BodyTest bodyTest){
        System.out.println(bodyTest);
        return new BodyTest("李斯","庆国");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BodyTest{
        String name = "张三";
        String address = "上海";
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Example.class, args);
//        run.close();
    }

}