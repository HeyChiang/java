package com.spring.intercept;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 获取ResponseBody参数并保存
 *
 * @author Chiang
 */
@ControllerAdvice
public class MyControllerAdvice implements ResponseBodyAdvice<Example.BodyTest> {

    /**
     * 根据返回的class类型来判断是否要获取返回的内容
     * @param aClass class类型
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        System.out.println("MyControllerAdvice supports():" + getClass().getSimpleName());
        return methodParameter.getParameterType() == Example.BodyTest.class;
    }

    /**
     * 获取要返回数据的 ResponseBody
     */
    @Override
    public Example.BodyTest beforeBodyWrite(Example.BodyTest body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest request, ServerHttpResponse serverHttpResponse) {
        //通过RequestContextHolder获取request
        HttpServletRequest httpServletRequest =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        HttpSession httpSession = httpServletRequest.getSession(true);
        httpSession.setAttribute("body", body);
        body.address = "beforeBodyWrite被修改成：去你的";
        System.out.println("MyControllerAdvice："+body);
        return body;
    }
}
