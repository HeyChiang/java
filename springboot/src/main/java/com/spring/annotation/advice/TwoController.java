package com.spring.annotation.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 演示在单个Controller里的异常捕捉
 */
@Controller
public class TwoController {


  @GetMapping("/login_two")
  @ResponseBody
  public String login(Integer password) throws Exception {
    if(password != 1){
        throw new Exception("来自Controller的Exception错误");
    }
    return "success";
  }
}