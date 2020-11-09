package com.chiang.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
         System.out.println("postProcessBeforeInitialization初始化前的方法..."+ bean.getClass().getSimpleName() + " beanName:"+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
         System.out.println("postProcessAfterInitialization初始化后方法..." + bean.getClass().getSimpleName()+ " beanName:"+beanName);
        return bean;
    }
}