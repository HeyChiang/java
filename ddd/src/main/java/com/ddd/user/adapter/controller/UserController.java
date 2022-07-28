package com.ddd.user.adapter.controller;

import com.ddd.infracore.http.Result;
import com.ddd.user.adapter.vo.UserVO;
import com.ddd.user.application.dto.UserDto;
import com.ddd.user.application.repository.UserRepository;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@Setter(onMethod_ = @Autowired)
public class UserController {

    /**
     * 只是简单的查询可以直接用repository，如果处理复杂的需要引入service，
     * 另外controller不能直接使用领域，应当通过service去操作
     */
    private UserRepository userRepository;

    /**
     * @param id 用户ID
     * @return 返回用户数据对象
     */
    @GetMapping("/get")
    public Result<UserVO> getUserById(Long id){
        if(Objects.nonNull(id)){
            UserDto userDto = userRepository.selectUserById(id);
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userDto,userVO);
            return Result.success(userVO);
        }
        return Result.failed("查询ID不能为空");
    }
}
