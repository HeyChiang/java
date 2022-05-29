package com.mybatis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContextUtils implements ApplicationContextAware {

    //Retained factories
    private static ApplicationContext applicationContext;

    //Pass the created factory to this class as a parameter
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    //Provides methods to get objects in the factory
    //RedisTemplate redisTemplate
    public static Object getBean(String beanName) {
        if (beanName == null && beanName.equals(" ")) {
            return null;
        }
        return applicationContext.getBean(beanName);
    }
}