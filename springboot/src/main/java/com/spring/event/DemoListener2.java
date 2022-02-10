package com.spring.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DemoListener2 {

    @EventListener
    public void onApplicationEvent(DemoEvent demoEvent) {
        System.out.println(">>>>>>>>>DemoListener2>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("消息：" + demoEvent.getId() + ":" + demoEvent.getMessage());
        System.out.println("执行使用线程：" + Thread.currentThread().getName());
    }
}
