package com.spring.filter_interceptor;
 
import java.io.IOException;
 
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Spring的过滤器，一般用于验证Token.
 * @order 用于给过滤器排序
 * @author Chiang
 */
@Component
@Order(1)
public class TestFilterOne extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("One doFilterInternal start");

        // 可以中途拦截直接返回，不传递过滤器
//        response.setStatus(200);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");
//        response.getWriter().println("have nothing");

        filterChain.doFilter(request, response);
        System.out.println("One doFilterInternal end");
    }
 
}