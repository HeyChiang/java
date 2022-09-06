package com.mybatisplus.demo.mapper;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author JiangHao at 10/31/2021 11:20 AM
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OracleMapperTest {

    @Resource
    private OracleMapper mapper;

    @Test
    @Order(1)
    void test1() {
        int test = mapper.test();
        System.out.println(test);
    }

    @Test
    @Order(2)
    void erpTest(){
        mapper.addInsertTable1(8256930);
    }
}