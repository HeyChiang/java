package com.mybatisplus.demo.service;

import com.mybatisplus.demo.mapper.MySQLMapper;
import com.mybatisplus.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JiangHao at 10/31/2021 10:59 AM
 */
@SpringBootTest
class UserServiceTest {


    @Resource
    private MySQLMapper mySQLMapper;

    @Resource
    private UserService userService;


    @Test
    public void intoDataBySelect(){
        boolean status = mySQLMapper.intoDataBySelect();
        assert status;
    }


    @Test
    public void saveBatch() throws Exception {
        List<User> users = mySQLMapper.selectList(null);

        users.forEach(System.out::println);

        users.forEach( user -> {
            user.setId(user.getId()+10);
        });

        userService.saveBatch(users);
    }


}