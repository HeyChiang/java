package com.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jianghao
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.mybatis"})
public class SampleMybatisApplication  {


  public static void main(String[] args) {
    SpringApplication.run(SampleMybatisApplication.class, args);
  }

}