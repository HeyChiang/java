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

    public interface MySource {

        @Output("output1")
        MessageChannel output1();

    }

    public interface MySink {

        @Input("input1")
        SubscribableChannel input1();
    }
}
