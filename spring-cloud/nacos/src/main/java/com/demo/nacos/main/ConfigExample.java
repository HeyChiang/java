package com.demo.nacos.main;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 通过代码控制dataId文件的发布、监听、删除
 *
 * 资料来源：https://github.com/alibaba/nacos/blob/develop/example/src/main/java/com/alibaba/nacos/example/ConfigExample.java
 *
 * @author JiangHao
 */
public class ConfigExample {

    public static void main(String[] args) throws NacosException, InterruptedException {
        String serverAddr = "localhost";
        String dataId = "nacos-config-custom.properties";
        String group = "DEFAULT_GROUP";
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println("初始化："+content);

        // 监听dataId发生的改变时候会通知
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("receive:" + configInfo);
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        });

        // 发布新的Nacos中dataId内容
        boolean isPublishOk = configService.publishConfig(dataId, group, "content");
        System.out.println("isPublishOk："+isPublishOk);

        // 获取新发布的dataId内容
        Thread.sleep(3000);
        content = configService.getConfig(dataId, group, 5000);
        System.out.println("configService.getConfig："+content);


        // 删除发dataId文件
        boolean isRemoveOk = configService.removeConfig(dataId, group);
        System.out.println("isRemoveOk："+isRemoveOk);
        Thread.sleep(3000);

        // 获取dataId发布的内容
        content = configService.getConfig(dataId, group, 5000);
        System.out.println("configService.getConfig："+content);
        Thread.sleep(300000);

    }
}