package com.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 设置Event事件的监听，可以有多个监听者不冲突
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {

    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
        System.out.println(">>>>>>>>>DemoListener>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("消息：" + demoEvent.getMessage());
        System.out.println("执行使用线程：" + Thread.currentThread().getName());
    }
}
