package com.chiang.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MyBeanAware implements BeanNameAware, BeanFactoryAware, ApplicationContextAware , InitializingBean ,DisposableBean{
    @Override
    public void setBeanName(String name) {
        System.out.println("MyBeanAware setBeanName:"+name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanAware setBeanFactory:");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("MyBeanAware setApplicationContext:");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MyBeanAware afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("MyBeanAware destroy");
    }
}
