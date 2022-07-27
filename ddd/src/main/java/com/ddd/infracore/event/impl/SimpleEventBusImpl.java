package com.ddd.infracore.event.impl;

import com.ddd.infracore.event.DomainEvent;
import com.ddd.infracore.event.DomainEventBus;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Setter(onMethod_ = @Autowired)
public class SimpleEventBusImpl implements DomainEventBus {

    private ApplicationContext applicationContext;

    @Override
    public void post(DomainEvent event) {
        applicationContext.publishEvent(event);
    }
}