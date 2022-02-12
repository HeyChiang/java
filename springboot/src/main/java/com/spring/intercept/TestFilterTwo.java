package com.spring.intercept;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring的过滤器
 * @author Chiang
 */
@Component
@Order(2)
public class TestFilterTwo extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("Two doFilterInternal start");
        filterChain.doFilter(request, response);
        System.out.println("Two doFilterInternal end");

    }

}
