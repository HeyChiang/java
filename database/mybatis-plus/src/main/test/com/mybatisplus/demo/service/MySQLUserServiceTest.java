package com.mybatisplus.demo.service;

import com.mybatisplus.demo.mapper.MySQLMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 在Test模式下，事务会自动回滚，所以用Rollback=false
 * @author JiangHao at 10/31/2021 10:59 AM
 */
@Rollback(value = false)
@SpringBootTest
class MySQLUserServiceTest {


    @Resource
    private MySQLMapper mySQLMapper;

    @Resource
    private MySQLUserService userService;


    @Test
    @Transactional
    public void intoDataBySelect(){
        boolean status = mySQLMapper.intoDataBySelect();
        throw new RuntimeException("test");
    }

}