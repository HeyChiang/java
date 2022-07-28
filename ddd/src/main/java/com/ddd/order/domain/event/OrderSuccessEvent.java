package com.ddd.order.domain.event;

import com.ddd.infracore.event.DomainEvent;
import com.ddd.order.application.dto.BuyProductDto;
import com.ddd.order.domain.entity.Order;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * 订单创建成功事件
 *
 * @author JiangHao
 */

public class OrderSuccessEvent extends DomainEvent {
    private List<BuyProductDto> productList;
    private Long userId;
    private BigDecimal totalPrice;

    public OrderSuccessEvent(Object source) {
        super(source);
        Order order = (Order) source;
        productList = order.getProductList();
        this.userId = order.getUser().getId();
        this.totalPrice = order.getTotalPrice();
    }

    @Override
    public String key() {
        return "OrderSuccess-"+UUID.randomUUID();
    }

    public List<BuyProductDto> getProductList() {
        return productList;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
