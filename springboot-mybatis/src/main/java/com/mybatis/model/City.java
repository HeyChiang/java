package com.mybatis.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Chiang
 */
@Data
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String cityName;
}
