package com.chiang.spring.lifecycle;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MySpringBean  implements InitializingBean {

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

  public void initMethod(){
    System.out.println("MySpringBean onInitialize");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("MySpringBean afterPropertiesSet");
  }
}