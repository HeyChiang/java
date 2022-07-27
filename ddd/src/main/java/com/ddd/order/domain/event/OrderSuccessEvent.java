package com.ddd.order.domain.event;

import com.ddd.infracore.event.DomainEvent;

import java.util.UUID;

/**
 * 订单创建成功事件
 *
 * @author JiangHao
 */
public class OrderSuccessEvent extends DomainEvent {
    public OrderSuccessEvent(Object source) {
        super(source);
    }

    @Override
    public String key() {
        return "OrderSuccess-"+UUID.randomUUID();
    }
}
