package com.mybatisplus.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mybatisplus.demo.mapper.MySQLMapper;
import com.mybatisplus.demo.model.MySQLUser;
import org.springframework.stereotype.Service;

@Service
public class MySQLUserService extends ServiceImpl<MySQLMapper, MySQLUser> {

}