package com.mybatis.mapper;

import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Setter(onMethod_ = {@Autowired})
public class CityMapperTest {

    private CityMapper cityMapper;
    private CityXMLMapper cityXMLMapper;

    @org.junit.Test
    public void deleteById() {
        this.cityMapper.deleteById(1);
    }

    @Test
    public void findByCity() {
        System.out.println(this.cityXMLMapper.findByCity("shanghai"));
    }

    @Test
    public void queryByIds(){
        List<Integer> ids = Arrays.asList(1,2,3);
        System.out.println(this.cityMapper.queryByIds(ids));
    }
}