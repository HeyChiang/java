package com.spring.lifecyle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 只打印自己定义的my开头bean
        if (beanName.contains("my")) {
            System.out.println("postProcessBeforeInitialization初始化前的方法..." + bean.getClass().getSimpleName() + " beanName:" + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        if (beanName.contains("my")) {
            System.out.println("postProcessAfterInitialization初始化后方法..." + bean.getClass().getSimpleName() + " beanName:" + beanName);
        }
        return bean;
    }
}