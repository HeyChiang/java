package com.ddd.user.application.dto;

import com.ddd.user.infrastructure.dataobject.UserDO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

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

    public static UserDto copyFrom(UserDO userDO){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDO,userDto);
        return userDto;
    }
}
