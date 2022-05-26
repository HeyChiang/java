package com.mybatis;

import com.mybatis.model.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 这个是注解查询,使用这个方法就不需要使用xml了
 * @author jianghao
 */
@Mapper
@Repository
public interface CityMapper {

  @Select("SELECT * FROM CITY WHERE city_name = #{state}")
  City findByCity(@Param("state") String state);

}