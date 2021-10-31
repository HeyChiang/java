package com.mybatisplus.demo.service;

import com.mybatisplus.demo.mapper.UserMapper;
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
    private UserMapper userMapper;

    @Resource
    private UserService userService;


    @Test
    public void intoDataBySelect(){
        boolean status = userMapper.intoDataBySelect();
        assert status;
    }


    @Test
    public void saveBatch() throws Exception {
        List<User> users = userMapper.selectList(null);

        users.forEach(System.out::println);

        users.forEach( user -> {
            user.setId(user.getId()+10);
        });

        userService.saveBatch(users);
    }


}