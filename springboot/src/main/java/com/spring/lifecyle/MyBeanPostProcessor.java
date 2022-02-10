package com.spring.lifecyle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 直接用LifeSpringBean 实现 BeanPostProcessor 不会打印，不知道为啥
 * @author Chiang
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 只打印自己定义的my开头bean
        if (beanName.equalsIgnoreCase(LifeSpringBean.CLASS_NAME)) {
            System.out.println("BeanPostProcessor -> postProcessBeforeInitialization：" + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        if (beanName.equalsIgnoreCase(LifeSpringBean.CLASS_NAME)) {
            System.out.println("BeanPostProcessorpost -> ProcessAfterInitialization：" + beanName);
        }
        return bean;
    }
}
