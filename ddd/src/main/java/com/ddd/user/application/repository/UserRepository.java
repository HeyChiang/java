package com.ddd.user.application.repository;

import com.ddd.user.application.dto.UserDto;

/**
 * 查询用户资料，用于数据的组装成领域对象
 *
 * @author JiangHao
 */
public interface UserRepository {

    /**
     * 根据用户ID 查询用户数据
     *
     * @param id 用户ID
     * @return 用户数据对象
     */
    UserDto selectUserById(Integer id);
}
