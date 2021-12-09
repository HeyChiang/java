package com.mybatisplus.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("company")
public class Company {
	private Long id;
    private String name;
    List<User> userList;

}
