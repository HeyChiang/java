package com.spring.annotation.advice;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常捕捉处理
 * @author Chiang
 */
@ControllerAdvice
public class GlobalHandler {

    /**
     * 1. 定义全局的数据绑定，无论在哪个Controller里都可以使用，每有一次请求都会触发一次调用。
     */
    @ModelAttribute(name = "md")
    public Map<String,Object> modelData() {
        System.out.println("调用了一次!!!!");

        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 99);
        map.put("gender", "男");
        return map;
    }


    /**
     * 2.如果不加@ResponseBody就可以返回一个view页面
     */
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Map<String,String> errorMissingHandler(Exception ex) {
        Map<String,String> map = new HashMap<>(2);
        map.put("code", "400");
        map.put("msg","缺少必需参数："+ex.getMessage());
        return map;
    }

    /**
     * 所有其他的异常拦截
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String,String> errorHandler(Exception ex) {
        HashMap<String,String> map = new HashMap<>(2);
        map.put("code", "500");

        map.put("msg","不知道是什么错误："+ex.getMessage());
        return map;
    }

    /**
     * 3. 对接收的数据类型进行预处理
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                // 这里可以将text转成固定的Data数据类型
                setValue(text);
            }
        });
    }
}
