package com.spring.annotation.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class ExceptionHandlingController {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @GetMapping("/login")
  @ResponseBody
  public String login(Integer password){
    if(password != 1){
        throw  new PasswordException("密码错误");
    }
    return "success";
  }

  @GetMapping("/data")
  @ResponseBody
  public String accessData(Integer password){
    if(password != 1){
      throw  new DataAccessException("访问数据库错误");
    }
    return "success";
  }

  @GetMapping("/order")
  @ResponseBody
  public String getOrder(Integer password){
    if(password != 1){
      throw  new RuntimeException();
    }
    return "success";
  }

  /**
   * 返回409 conflict异常状态给前端
   */
  @ResponseStatus(value= HttpStatus.CONFLICT, reason="Data integrity violation")
  @ExceptionHandler(PasswordException.class)
  public void conflict() {
    // Nothing to do
  }

  /**
   * 置顶一个视图的名字将用于错误显示，如果增加了 @ResponseBody 注解，将直接返回字符串
   */
  @ExceptionHandler({SQLException.class,DataAccessException.class})
  public String databaseError() {
    // Nothing to do.  Returns the logical view name of an error page, passed
    // to the view-resolver(s) in usual way.
    // Note that the exception is NOT available to this view (it is not added
    // to the model) but see "Extending ExceptionHandlerExceptionResolver"
    // below.
    return "databaseError";
  }

  // Total control - setup a model and return the view name yourself. Or
  // consider subclassing ExceptionHandlerExceptionResolver (see below).
  @ExceptionHandler(Exception.class)
  public ModelAndView handleError(HttpServletRequest req, Exception ex) {
    logger.error("Request: " + req.getRequestURL() + " raised " + ex);

    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", ex);
    mav.addObject("url", req.getRequestURL());
    mav.setViewName("error");
    return mav;
  }
}