package com.example;

import com.example.model.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

//@Mapper
//@Repository
public interface CityMapper {

//  @Select("SELECT * FROM CITY WHERE city_name = #{state}")
  City findByCity(@Param("state") String state);

}