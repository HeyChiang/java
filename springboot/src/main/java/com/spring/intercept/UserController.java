package com.spring.intercept;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JiangHao at 2022/2/12 16:19
 */
@RestController
@Slf4j
public class UserController {


    @RequestMapping("/")
    String home() {
        System.out.println("Controller be called!!");
        return "success";
    }

    @RequestMapping("/bodyMappingTest")
    @ResponseBody
    UserBodyBean bodyTestMethod(@RequestBody UserBodyBean userBody){
        log.info("请求的参数：{}", userBody);
        return new UserBodyBean("李斯","庆国");
    }
}
