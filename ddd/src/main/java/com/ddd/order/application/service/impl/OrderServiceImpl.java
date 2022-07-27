package com.ddd.order.application.service.impl;

import com.ddd.order.application.dto.OrderDto;
import com.ddd.order.application.service.OrderService;
import com.ddd.order.domain.entity.Order;
import com.ddd.product.application.dto.ProductDto;
import com.ddd.product.application.service.ProductService;
import com.ddd.user.application.dto.UserDto;
import com.ddd.user.application.service.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author JiangHao
 */
@Service
@Setter(onMethod_ = @Autowired)
public class OrderServiceImpl implements OrderService {

    private ProductService productService;
    private UserService userService;

    @Override
    public Order createOrder(OrderDto orderDto) {
        List<ProductDto> productList = productService.selectAll(orderDto.getProductIds());
        UserDto userDto = userService.selectUserById(orderDto.getUserId());
        Order order = Order.builder().productList(productList).user(userDto).build();
        order.create();

        return null;
    }
}
