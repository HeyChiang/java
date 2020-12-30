package com.spring.annotation.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 演示在这个Controller里抛异常不会被其他Controller里定义的@ExceptionHandler捕捉到
 */
@Controller
public class AnotherExceptionHandlingController {

  @GetMapping("/login_another")
  @ResponseBody
  public String login(Integer password){
    if(password != 1){
        throw  new PasswordException("密码错误");
    }
    return "success";
  }
}