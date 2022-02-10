package com.spring.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class DemoEvent extends ApplicationEvent {
    private Long id;
    private String message;

    public DemoEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }
}
