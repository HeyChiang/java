package com.ddd.order.application.repository.impl;

import com.ddd.order.application.repository.OrderRepository;
import com.ddd.order.domain.entity.Order;
import com.ddd.order.infrastructure.dataobject.OrderDO;
import com.ddd.order.infrastructure.mapper.OrderMapper;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Setter(onMethod_ = @Autowired)
public class OrderRepositoryImpl implements OrderRepository {

    private OrderMapper orderMapper;

    @Override
    public int insert(Order order) {
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(order,orderDO);
        int insert = orderMapper.insert(orderDO);
        order.setOrderId(orderDO.getId());
        return insert;
    }
}
