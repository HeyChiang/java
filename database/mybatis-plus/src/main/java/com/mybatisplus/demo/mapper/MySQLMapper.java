package com.mybatisplus.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatisplus.demo.model.MySQLUser;
import org.apache.ibatis.annotations.Insert;

/**
 * Application配置MapperScan会扫描到这里
 * @author jianghao
 */
public interface MySQLMapper extends BaseMapper<MySQLUser> {


    /**
     * 查询数据并插入到另外的表中
     */
    @Insert("insert into user2 select * from user")
    boolean intoDataBySelect();
}