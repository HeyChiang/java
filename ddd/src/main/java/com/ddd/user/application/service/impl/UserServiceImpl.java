package com.ddd.user.application.service.impl;

import com.ddd.user.application.dto.UserDto;
import com.ddd.user.application.service.UserService;
import com.ddd.user.infrastructure.dataobject.UserDO;
import com.ddd.user.infrastructure.mapper.UserMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户信息服务
 * @author JiangHao
 */
@Service
@Setter(onMethod_= @Autowired)
public class UserServiceImpl implements UserService {

    private UserMapper mapper;

    @Override
    public UserDto selectUserById(Long id) {
        UserDO userDO = mapper.selectUserById(id);
        return UserDto.copyFrom(userDO);
    }
}
