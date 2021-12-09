package com.mybatisplus.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mybatisplus.demo.model.Company;
import com.mybatisplus.demo.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface H2CompanyMapper extends BaseMapper<Company> {

    @Select("SELECT c.id, c.name, u.id AS uid, u.name AS uname, u.age, u.email " +
            " FROM company c JOIN user u ON c.id = u.company_id WHERE c.id=1")
    List<Company> testResultMapCollection(IPage<Company> page);
}
