package com.ddd.user.application.repository.impl;

import com.ddd.user.application.dto.UserDto;
import com.ddd.user.application.repository.UserRepository;
import com.ddd.user.infrastructure.dataobject.UserDO;
import com.ddd.user.infrastructure.mapper.UserMapper;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 *
 * @author JiangHao
 */
@Repository
@Setter(onMethod_= @Autowired)
public class UserRepositoryImpl implements UserRepository {

    private UserMapper userMapper;

    @Override
    public UserDto selectUserById(Integer id) {
        UserDO userDO = userMapper.selectUserById(id);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDO, userDto);
        return userDto;
    }
}
