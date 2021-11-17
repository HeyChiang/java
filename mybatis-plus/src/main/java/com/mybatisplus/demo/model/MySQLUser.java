package com.mybatisplus.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(excludeProperty = "email")
public class MySQLUser {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}