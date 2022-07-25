package com.ddd.user.application.service;

import com.ddd.user.application.dto.UserDto;

/**
 * 查询用户资料
 *
 * @author JiangHao
 */
public interface UserService {

    UserDto selectUserById(Integer id);
}
