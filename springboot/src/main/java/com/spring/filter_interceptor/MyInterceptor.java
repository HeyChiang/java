package com.spring.filter_interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * @author Chiang
 */
@Configuration
public class MyInterceptor implements HandlerInterceptor{
    
    /**
     * 在DispatcherServlet之前执行
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        System.out.println("************ preHandle BaseInterceptor executed**********");
        return true;
    }

    /**
     * 在controller执行之后的DispatcherServlet之后执行
     * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
        System.out.println("************ postHandle BaseInterceptor executed**********");
    }

    /**
     * 在页面渲染完成返回给客户端之前执行
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        System.out.println("************ afterCompletion BaseInterceptor executed**********");
    }
}