package com.demo.nacos.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * 1. nacos设置参数用的是post，否则无法设置成功
 * 2. 使用 RefreshScope 注解开启自动刷新，也可以使用 Configuration 和它搭配创建一个配置类
 * @author jianghao
 */
@Controller
@RefreshScope
@RequestMapping("config")
public class ConfigController {

    @Value("${custome.test:lisi}")
    private String test;

    /**
     * 标记useLocalCache参数可以通过nacos动态改变，默认是false.
     */
    @Value("${useLocalCache:false}")
    private boolean useLocalCache;


    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public String get() {
        return "useLocalCache: "+useLocalCache +"\n\n test:"+test;
    }
}