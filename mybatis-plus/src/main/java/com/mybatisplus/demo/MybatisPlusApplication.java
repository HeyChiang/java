package com.mybatisplus.demo;

import com.mybatisplus.demo.mapper.MySQLMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 通过MybatisPlus直接查询数据库
 * @author jianghao
 */
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.mybatisplus.demo.mapper")
public class MybatisPlusApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

    @Resource
    private MySQLMapper mySQLMapper;

    @Override
    public void run(String... args) throws Exception {
        intoDataBySelect();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void intoDataBySelect(){
        boolean status = mySQLMapper.intoDataBySelect();

        throw new RuntimeException("za");
    }
}