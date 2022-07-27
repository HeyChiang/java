package com.ddd.infracore.event;

/**
 * 事件总线，发送各种领域的消息
 *
 * @author jianghao
 */
public interface DomainEventBus {

  void post(DomainEvent event);

}
