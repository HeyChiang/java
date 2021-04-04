package com.demo.nacos.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * 获取用get请求，nacos设置参数用的是post，否则无法设置成功
 * @author jianghao
 */
@Controller
@RequestMapping("config")
public class ConfigController {

    /**
     * 标记useLocalCache参数可以通过nacos动态改变，默认是false
     */
    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }
}