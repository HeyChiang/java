package com.spring.component;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 配合Gateway获取参数
 *
 * @author jianghao
 */
@RestController
@SpringBootApplication
public class GatewayExample {

    @RequestMapping("/get")
    String home(HttpServletRequest request,String name) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String element = headerNames.nextElement();
            System.out.println(element + " : "+request.getHeader(element));
        }
        return "name：" + name;
    }

    @PostMapping("/post")
    String post(HttpServletRequest request,@RequestBody String name) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String element = headerNames.nextElement();
            System.out.println(element + " : "+request.getHeader(element));
        }
        return "name：" + name;
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayExample.class, args);
    }

}