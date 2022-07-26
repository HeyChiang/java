package com.ddd.user.adapter.controller;

import com.ddd.infracore.http.Result;
import com.ddd.user.adapter.vo.UserVO;
import com.ddd.user.application.dto.UserDto;
import com.ddd.user.application.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 用户请求空置期
 *
 * @author JiangHao
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;

    @GetMapping("/get")
    public Result<UserVO> getUserById(Integer id){
        if(Objects.nonNull(id)){
            UserDto userDto = userRepository.selectUserById(id);
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userDto,userVO);
            return Result.success(userVO);
        }
        return Result.failed("查询ID不能为空");
    }
}
