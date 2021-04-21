package com.chiang.sentinel.controller;

import com.chiang.sentinel.service.AnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jianghao
 */
@RestController
public class TestController {

    private final AnnotationService service;

    public TestController(AnnotationService service) {
        this.service = service;
    }

    /**
     * 该资源被SentinelAspectConfiguration定义熔断降级，每秒请求超过1次就熔断
     */
    @GetMapping(value = "/hello/{name}")
    public String apiHello(@PathVariable String name) {
        return service.hello(1);
    }
}
