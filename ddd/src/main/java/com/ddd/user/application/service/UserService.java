package com.ddd.user.application.service;

import com.ddd.user.application.dto.UserDto;

public interface UserService {

    /**
     * 根据用户ID 查询用户数据
     *
     * @param id 用户ID
     * @return 用户数据对象
     */
    UserDto selectUserById(Long id);
}
