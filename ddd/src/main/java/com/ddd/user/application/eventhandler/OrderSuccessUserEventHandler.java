package com.ddd.user.application.eventhandler;

import com.ddd.infracore.event.DomainEventHandler;
import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.event.OrderSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * 订单成功事件处理
 * @author JiangHao
 */
@Component
public class OrderSuccessUserEventHandler implements DomainEventHandler<OrderSuccessEvent> {

    @Override
    public void onApplicationEvent(OrderSuccessEvent event) {
        Order order = (Order) event.getSource();
        System.out.println("我是用户，要扣钱："+order.getTotalPrice());
    }
}
