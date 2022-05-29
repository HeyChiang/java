package com.mybatisplus.demo.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mybatisplus.demo.mapper.H2CompanyMapper;
import com.mybatisplus.demo.model.Company;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@DS("h2")
public class H2CompanyService extends ServiceImpl<H2CompanyMapper, Company> {

    @Resource
    private H2CompanyMapper mapper;

    public List<Company> testResultMapCollection(IPage<Company> page, @Param("ew") Wrapper<Company> queryWrapper){
        return mapper.testResultMapCollection(page,queryWrapper);
    }
}
