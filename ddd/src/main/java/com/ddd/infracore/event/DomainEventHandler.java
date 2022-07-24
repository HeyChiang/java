package com.ddd.infracore.event;

import org.springframework.context.ApplicationListener;

/**
 * 监听各个领域事件的接口
 *
 * @author jianghao
 */
public interface DomainEventHandler<T extends DomainEvent> extends ApplicationListener<T> {

}
