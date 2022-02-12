package com.spring.filter_interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

/**
 * Tomcat的过滤器，一般用于验证Token，为了兼容不同的web容器，需要继承OncePerRequestFilter
 *
 * @author Chiang
 * @order 用于给过滤器排序
 */
@Component
@Order(1)
@Slf4j
@SuppressWarnings("all")
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

        // request 和 response 的流一旦被读过以后，就不能使用了，所以需要ContentCaching包装下
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        log.info("TestFilterOne  requestWrapper.getContentAsByteArray()：{}", new String(requestWrapper.getContentAsByteArray(),response.getCharacterEncoding()));

        filterChain.doFilter(requestWrapper, responseWrapper);

        log.info("TestFilterOne  responseWrapper.getContentAsByteArray()：{}", new String(responseWrapper.getContentAsByteArray(),response.getCharacterEncoding()));
        // 需要将body写回到响应里
        responseWrapper.copyBodyToResponse();

        System.out.println("One doFilterInternal end");
    }

    /**
     * 获取请求body 只能被读取一次，多次读取要自定义HttpServletRequestWrapper 和 DispatcherServlet
     * @param request
     * @throws IOException
     */
    private void readAndPrintInputStream(ContentCachingRequestWrapper request) throws IOException {

        String body = new String(request.getContentAsByteArray(), request.getCharacterEncoding());
        log.info("TestFilterOne  request.getInputStream()：{}", body);
    }


}
