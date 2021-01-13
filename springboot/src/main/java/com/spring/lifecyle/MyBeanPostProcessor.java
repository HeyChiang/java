package com.spring.lifecyle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 只打印自己定义的my开头bean
        if (beanName.equalsIgnoreCase(LifeSpringBean.CLASS_NAME)) {
            System.out.println("BeanPostProcessor->postProcessBeforeInitialization初始化前的方法..." + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        if (beanName.equalsIgnoreCase(LifeSpringBean.CLASS_NAME)) {
            System.out.println("BeanPostProcessorpost->ProcessAfterInitialization初始化后方法..." + beanName);
        }
        return bean;
    }
}