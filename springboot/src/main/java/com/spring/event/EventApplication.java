package com.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EventApplication implements CommandLineRunner {

    @Autowired
    private ApplicationEventPublisher publisher;

    public static void main(String[] args) {
        SpringApplication.run(EventApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // source 通常传入当前的类。the object on which the event
        // initially occurred or with which the event is associated (never null)
        publisher.publishEvent(new DemoEvent(this,"message"));
    }
}