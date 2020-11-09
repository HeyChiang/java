package com.chiang.spring.lifecycle;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class MyTestBean {

    @Value("zhangsan")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bean(initMethod = "initMethod", destroyMethod = "onDestroy")
    public MySpringBean myTestSpringBean() {
        return new MySpringBean();
    }

}