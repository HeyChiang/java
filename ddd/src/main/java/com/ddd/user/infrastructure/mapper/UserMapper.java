package com.ddd.user.infrastructure.mapper;

import com.ddd.user.infrastructure.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户数据访问类
 *
 * @author JiangHao
 */
public interface UserMapper {

    /**
     * 根据用户ID查询用户数据
     *
     * @param id 用户ID
     * @return 用户数据
     */
    @Select("select * from user where id = #{id}")
    UserDO selectUserById(Long id);

}
