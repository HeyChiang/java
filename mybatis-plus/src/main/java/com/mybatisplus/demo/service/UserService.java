package com.mybatisplus.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mybatisplus.demo.mapper.UserMapper;
import com.mybatisplus.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}