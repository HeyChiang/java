package com.mybatis;

import com.mybatis.mapper.CityXMLMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author jianghao
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.mybatis-xml.mapper"})
public class SampleMybatisApplication implements CommandLineRunner {

  @Resource
  private CityXMLMapper cityMapper;

  public static void main(String[] args) {
    SpringApplication.run(SampleMybatisApplication.class, args);
  }

  @Override
  public void run(String... args) {
    System.out.println(this.cityMapper.findByCity("shanghai"));
    System.out.println("第二次使用缓存查询");
    System.out.println(this.cityMapper.findByCity("shanghai"));
  }

}