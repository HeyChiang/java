/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dubbo.consumer;

import com.dubbo.service.ByeService;
import com.dubbo.service.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Dubbo Registry ZooKeeper Consumer Bootstrap
 *
 */
@SpringBootApplication
@RestController
public class ApacheDubboConsumerBootstrap {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 通过dubbo直接去调用远程服务
     * version: 调用服务的版本
     * group：调用服务的分组
     * loadbalance：集群负载均衡时，Dubbo 提供了多种均衡策略，缺省为 random 随机调用。如果服务者和消费者都有loadbalance则按照
     * url="dubbo://localhost:20890"，可以直接连接服务，注意provider要固定好端口不能使用-1随机
     */
    @DubboReference(version = "${demo.service.version}" ,loadbalance = "roundrobin",group = "myGroup")
    private DemoService demoService;

    @DubboReference(version = "${demo.service.version}" ,loadbalance = "roundrobin",group = "myGroup")
    private ByeService byeService;

    public static void main(String[] args) {
        SpringApplication.run(ApacheDubboConsumerBootstrap.class);
    }

    @Bean
    public ApplicationRunner runner() {
        String sayBye = byeService.sayBye("刘牛牛");
        String sayHello = demoService.sayHello("我是蒋犇犇");

        return args -> logger.info(sayHello +" -- "+sayBye);
    }


    @GetMapping("/test")
    public String test(){

        logger.info(demoService.sayHello("成功了"));
        return "success";
    }


}
