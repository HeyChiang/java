package com.mybatisplus.demo;

import com.mybatisplus.demo.mapper.UserMapper;
import com.mybatisplus.demo.model.User;
import com.mybatisplus.demo.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.List;

/**
 * 通过MybatisPlus直接查询数据库
 * @author jianghao
 */
@SpringBootApplication
@MapperScan("com.mybatisplus.demo.mapper")
public class MybatisPlusApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;


    public List<User> testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
        return userList;
    }

    @Override
    public void run(String... args) throws Exception {
        List<User> users = testSelect();
        saveBatch(users);
    }

    private void saveBatch(List<User> users) {
        System.out.println(("----- saveBatch method test ------"));
        users.forEach( user -> {
            user.setId(user.getId()+10);
        });

        userService.saveBatch(users);
    }
}