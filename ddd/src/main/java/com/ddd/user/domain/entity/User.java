package com.ddd.user.domain.entity;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * 用户领域
 */
@Data
public class User {

    private String phone;
    private String nickName;
    private String userName;
    private String password;
    private String address;

    public boolean save(@Validated User user){

        return false;
    }
}
