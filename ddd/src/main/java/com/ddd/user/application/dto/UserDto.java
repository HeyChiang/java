package com.ddd.user.application.dto;

import lombok.Data;

/**
 * 用户数据传输类
 *
 * @author JiangHao
 */
@Data
public class UserDto {
    private Integer id;
    private String phone;
    private String userName;
    private String password;
    private String address;
}
