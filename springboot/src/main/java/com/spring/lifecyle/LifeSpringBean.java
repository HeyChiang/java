package com.spring.lifecyle;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 显示一个完整的Bean生命周期
 */
@Configuration
public class LifeSpringBean implements InitializingBean, BeanNameAware, DisposableBean {

  public static final String CLASS_NAME = "LifeSpringBean";
  
  public LifeSpringBean(){
    System.out.println(CLASS_NAME+" 构造方法");
  }

  @PostConstruct
  public void postConstruct() {
    System.out.println(CLASS_NAME+"注解 @postConstruct");
  }

  @PreDestroy
  public void preDestroy() {
    System.out.println(CLASS_NAME+"注解 @preDestroy");
  }

  public void onDestroy(){
    System.out.println(CLASS_NAME+" onDestroy");
  }

  /**
   * 在此代码的配置下，会调用initMethod和onDestroy方法
   * @Bean(initMethod = "onInitialize", destroyMethod = "onDestroy")
   * public MySpringBean mySpringBean() {
   *    return new MySpringBean();
   * }
   */
  public void initMethod(){
    System.out.println(CLASS_NAME+" onInitialize");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println(CLASS_NAME+" InitializingBean->afterPropertiesSet");
  }

  @Override
  public void setBeanName(String name) {
    System.out.println(CLASS_NAME+" BeanNameAware->setBeanName："+name);
  }

  @Override
  public void destroy() throws Exception {
    System.out.println(CLASS_NAME+" DisposableBean->destroy");
  }
}