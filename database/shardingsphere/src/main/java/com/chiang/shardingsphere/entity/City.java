package com.chiang.shardingsphere.entity;


import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author Chiang
 */
@TableName
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String cityName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
