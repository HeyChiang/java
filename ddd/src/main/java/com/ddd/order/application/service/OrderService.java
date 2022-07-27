package com.ddd.order.application.service;

import com.ddd.order.application.dto.OrderDto;
import com.ddd.order.domain.entity.Order;

/**
 * 订单服务协调类
 *
 * @author JiangHao
 */
public interface OrderService {

    /**
     * 创建订单
     */
    Order createOrder(OrderDto orderDto);
}
