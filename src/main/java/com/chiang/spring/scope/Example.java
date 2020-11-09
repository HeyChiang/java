package com.chiang.spring.scope;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@SpringBootApplication
public class Example {

    private Integer count = 0;

    @RequestMapping("/")
    String home(HttpServletRequest request) {
        HttpSession session = request.getSession();
        count++;

        // Controller在单例的模式下，使用getBean的方式去创建就可以根据scope要求去生成bean了
        SessionTestBean bean = SpringBeanFactory.getBean(SessionTestBean.class);

        return "SessionID：" +
                session.getId() +
                "\n count：" +
                count +
                "\n beanCount：" +
                bean.getCount();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Example.class, args);
        run.close();
    }

}