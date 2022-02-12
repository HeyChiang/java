package com.spring.filter_interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 拦截器
 *
 * @author Chiang
 */
@Configuration
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 在 Controller 之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("************ preHandle BaseInterceptor executed：" + handler.getClass().getSimpleName());

        HandlerMethod method  = (HandlerMethod) handler;
        log.info("preHandler HandlerMethod:"+method.getBean());

        // 设置属性可以在过滤器获取
        request.setAttribute("testA", "Data");
        return true;
    }

    /**
     * 调用Controller方法之后（不抛出异常的情况下）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("************ postHandle BaseInterceptor executed**********");

        HandlerMethod method  = (HandlerMethod) handler;
        log.info("postHandle HandlerMethod:"+method.getBean());
    }

    /**
     * 在页面渲染完成返回给客户端之前执行（无论是否抛出异常）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("************ afterCompletion BaseInterceptor executed**********");
    }
}
