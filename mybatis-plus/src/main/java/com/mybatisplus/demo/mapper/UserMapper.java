package com.mybatisplus.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatisplus.demo.model.User;
import org.apache.ibatis.annotations.Insert;

/**
 * @author jianghao
 */
public interface UserMapper extends BaseMapper<User> {


    /**
     * 查询数据并插入到另外的表中
     */
    @Insert("insert into user2 select * from user where id =2;")
    boolean intoDataBySelect();
}