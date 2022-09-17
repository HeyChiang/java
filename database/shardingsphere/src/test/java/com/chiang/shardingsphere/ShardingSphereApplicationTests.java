package com.chiang.shardingsphere;

import com.chiang.shardingsphere.entity.City;
import com.chiang.shardingsphere.mapper.CityMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShardingSphereApplicationTests {

    @Autowired
    private CityMapper mapper;

    @Test
    void contextLoads() {
        for (int i = 0; i < 10; i++) {
            City room=new City();
            room.setId(i);
            room.setCityName("cityName"+i);
            int insert = mapper.insert(room);
            System.out.println("insert:"+insert);
        }
    }

}
