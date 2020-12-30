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
public class MySpringBean  implements InitializingBean, BeanNameAware, DisposableBean {

  @Value("${name}")
  private String testName;

  public MySpringBean(){
    System.out.println("MySpringBean 我被创建了,testName:" +testName);
  }

  @PostConstruct
  public void postConstruct() {
    System.out.println("MySpringBean postConstruct");
  }

  @PreDestroy
  public void preDestroy() {
    System.out.println("MySpringBean preDestroy");
  }

  public void onDestroy(){
    System.out.println("MySpringBean onDestroy");
  }

  /**
   * 在此代码的配置下，会调用initMethod和onDestroy方法
   * @Bean(initMethod = "onInitialize", destroyMethod = "onDestroy")
   * public MySpringBean mySpringBean() {
   *    return new MySpringBean();
   * }
   */
  public void initMethod(){
    System.out.println("MySpringBean onInitialize");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("MySpringBean afterPropertiesSet");
  }

  @Override
  public void setBeanName(String name) {
    System.out.println("MySpringBean setBeanName："+name);
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("MySpringBean destroy");
  }
}