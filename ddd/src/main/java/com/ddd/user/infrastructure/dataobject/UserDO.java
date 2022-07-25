package com.ddd.user.infrastructure.dataobject;

import lombok.Data;

/**
 * 数据库访问对象
 *
 * @author JiangHao
 */
@Data
public class UserDO {
    private Integer id;
    private String phone;
    private String userName;
    private String password;
    private String address;
}
