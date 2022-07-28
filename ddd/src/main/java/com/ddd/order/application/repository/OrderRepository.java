package com.ddd.order.application.repository;

import com.ddd.order.domain.entity.Order;
import com.ddd.order.infrastructure.mapper.OrderMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


public interface OrderRepository {

    int insert(Order order);
}
