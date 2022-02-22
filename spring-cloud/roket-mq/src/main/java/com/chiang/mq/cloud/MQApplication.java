package com.chiang.mq.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 使用 @EnableBinding 将 @Input 和 @Output 注解绑定到代理上去
 */
@SpringBootApplication
@EnableBinding({ MQApplication.MySource.class, MQApplication.MySink.class})
public class MQApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MQApplication.class, args);
    }

    @Autowired
    private SenderService senderService;

    @Override
    public void run(String... args) throws Exception {
        senderService.send("你好");
    }

    /**
     * 定义写的通道，通过 @Autowired 注入后可以直接发消息
     */
    public interface MySource {
        @Output("output1")
        MessageChannel output1();
    }

    /**
     * 定义读的通道
     */
    public interface MySink {
        @Input("input1")
        SubscribableChannel input1();
    }
}
