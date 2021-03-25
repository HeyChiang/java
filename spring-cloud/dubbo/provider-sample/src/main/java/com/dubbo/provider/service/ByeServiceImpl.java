package com.dubbo.provider.service;

import com.dubbo.service.ByeService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "${demo.service.version}",group = "myGroup")
public class ByeServiceImpl implements ByeService {
    @Override
    public String sayBye(String name) {
        return "bye bye" + name;
    }
}
