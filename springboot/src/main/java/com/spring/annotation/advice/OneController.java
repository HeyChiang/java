package com.spring.annotation.advice;

import com.spring.annotation.exception.DataAccessException;
import com.spring.annotation.exception.PasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * 演示在单个Controller里的异常捕捉
 */
@Controller
public class OneController {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @GetMapping("/login")
  @ResponseBody
  public String login(Integer password) throws MissingServletRequestParameterException {
    if(password != 1){
        throw new MissingServletRequestParameterException("MissError","密码错误");
    }
    return "success";
  }
}