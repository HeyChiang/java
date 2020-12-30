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

import brave.Tracer;
import com.dubbo.service.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DubboConsumerBootstrap {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 申请去调用服务者类，可
     * loadbalance：集群负载均衡时，Dubbo 提供了多种均衡策略，缺省为 random 随机调用。如果服务者和消费者都有loadbalance则按照
     */
    @DubboReference(version = "${demo.service.version}" ,loadbalance = "roundrobin")
    private DemoService demoService;

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerBootstrap.class);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> logger.info(demoService.sayHello("我是蒋犇犇"));
    }


    @Autowired
    private Tracer tracer;

    @GetMapping("/test")
    public String test(){
        tracer.currentSpan().tag("key1","value2").tag("key2","value2");

        logger.info(demoService.sayHello("成功了"));
        return "success";
    }


}
