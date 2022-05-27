package com.mybatisplus.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.demo.model.Company;
import com.mybatisplus.demo.service.H2CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AssociationTest {
    @Autowired
    private H2CompanyService service;

    @Test
    public void testResultMapCollection() {
        IPage<Company> page = new Page<>(1, 2);
        // 1.因为Company的类结构是一对多的，所以userList是空，如果替换script或者xml文件形式写mapper就可以有数据返回
        // 2. Wrapper的传入不会影响查询的结果
        QueryWrapper<Company> wrapper = new QueryWrapper<Company>().eq("id", 2);
        List<Company> company = service.testResultMapCollection(page,wrapper);
        System.out.println(company);
    }
}
