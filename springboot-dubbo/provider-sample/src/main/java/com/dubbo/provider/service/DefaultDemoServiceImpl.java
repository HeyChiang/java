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
package com.dubbo.provider.service;

import com.dubbo.service.DemoService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 服务者提供者实现api模块的DemoService接口，注解@DubboService来提供对外提供服务。
 * version：当一个接口实现，出现不兼容升级时，可以用版本号过渡，版本号不同的服务相互间不引用。
 * loadbalance：集群负载均衡时，Dubbo 提供了多种均衡策略，缺省为 random 随机调用。
 */
@DubboService(version = "${demo.service.version}",loadbalance = "roundrobin")
public class DefaultDemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return String.format("Hello  Hello, %s", name);
    }
}