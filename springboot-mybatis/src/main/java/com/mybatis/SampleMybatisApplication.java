package com.mybatis;

import com.mybatis.mapper.CityXMLMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.mapper"})
public class SampleMybatisApplication implements CommandLineRunner {

  @Resource
  private CityXMLMapper cityMapper;

  public static void main(String[] args) {
    SpringApplication.run(SampleMybatisApplication.class, args);
  }

  public void run(String... args) throws Exception {
    System.out.println(this.cityMapper.findByCity("changsha"));
    System.out.println("第二次使用缓存查询");
    System.out.println(this.cityMapper.findByCity("changsha"));
  }

}