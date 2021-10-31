package com.mybatisplus.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 通过MybatisPlus直接查询数据库
 * @author jianghao
 */
@SpringBootApplication
@MapperScan("com.mybatisplus.demo.mapper")
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }
}