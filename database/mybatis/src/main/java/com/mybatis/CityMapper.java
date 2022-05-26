package com.mybatis;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 这个是注解查询,使用这个方法就不需要使用xml了
 * @author jianghao
 */
@Mapper
@Repository
public interface CityMapper {

  @Delete("delete from CITY WHERE id = #{id}")
  void deleteById(@Param("id") int id);

}