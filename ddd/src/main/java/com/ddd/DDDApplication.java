package com.ddd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(basePackages = {"com.ddd.*.infrastructure.mapper"})
@EnableTransactionManagement
@SpringBootApplication
public class DDDApplication {


    public static void main(String[] args) {
        SpringApplication.run(DDDApplication.class, args);
    }

}