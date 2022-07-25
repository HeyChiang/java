package com.ddd.user.application.service.impl;

import com.ddd.user.application.dto.UserDto;
import com.ddd.user.application.service.UserService;
import com.ddd.user.infrastructure.dataobject.UserDO;
import com.ddd.user.infrastructure.mapper.UserMapper;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 *
 * @author JiangHao
 */
@Service
@Setter(onMethod_= @Autowired)
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Override
    public UserDto selectUserById(Integer id) {
        UserDO userDO = userMapper.selectUserById(id);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDO, userDto);
        return userDto;
    }
}
