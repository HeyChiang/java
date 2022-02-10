package com.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件类，用于发送广播事件
 */
public class DemoEvent extends ApplicationEvent {
    private final String message;

    /**
     * @param source 通常都是发布事件的类，传this
     * @param message 自定义的数据
     */
    public DemoEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
