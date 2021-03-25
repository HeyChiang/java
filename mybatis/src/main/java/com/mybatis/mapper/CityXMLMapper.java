package com.mybatis.mapper;

import com.mybatis.model.City;

public interface CityXMLMapper {

  City findByCity(String city);

}