package com.mybatis.mapper;

import com.mybatis.model.City;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 这个是注解查询,使用这个方法就不需要使用xml了
 * @author jianghao
 */
@Mapper
@Repository
public interface CityMapper {

  @Delete("delete from CITY WHERE id = #{id}")
  int deleteById(@Param("id") int id);

  /**
   * 字段名称的命名要和Java类的一样，否则无法查询出来
   */
  @Select("<script>" +
          "select id,city_name cityName from City where id in "+
          " <foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
          " #{id} " +
          "</foreach>" +
          "</script>")
  List<City> queryByIds(@Param("ids") List<Integer> ids);
}