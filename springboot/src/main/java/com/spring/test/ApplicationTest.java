package com.spring.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@SpringBootApplication
public class ApplicationTest {

    @RequestMapping("/get")
    public String get(HttpServletRequest request) {
        printHeader(request);
        return "success";
    }

    @RequestMapping("/delay")
    public String delay(HttpServletRequest request) {
        printHeader(request);
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

    private void printHeader(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();

        System.out.println("头信息");
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+":"+value);
        }
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ApplicationTest.class, args);
//        run.close();
    }

}