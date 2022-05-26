package com.mybatis;

import com.mybatis.mapper.CityXMLMapper;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Setter(onMethod_ = {@Autowired})
public class CityMapperTest {

    private CityMapper cityMapper;
    private CityXMLMapper cityXMLMapper;

    @Test
    public void deleteById() {
        this.cityMapper.deleteById(1);
    }

    @Test
    public void findByCity() {
        System.out.println(this.cityXMLMapper.findByCity("shanghai"));
    }
}