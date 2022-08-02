package com.ddd.order.domain.event;

import com.ddd.infracore.event.DomainEvent;
import com.ddd.order.domain.entity.BuyProduct;
import com.ddd.order.domain.entity.Order;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

/**
 * 订单创建成功事件
 *
 * @author JiangHao
 */

public class OrderSuccessEvent extends DomainEvent {
    private final HashMap<Long,BigDecimal> productMap;
    private final Long userId;
    private final BigDecimal totalPrice;

    public OrderSuccessEvent(Object source) {
        super("OrderSuccess");
        Order order = (Order) source;
        productMap = new HashMap<>(order.getProductList().size());
        for (BuyProduct buyProduct : order.getProductList()) {
            productMap.put(buyProduct.getId(),buyProduct.getBuyNum());
        }
        this.userId = order.getUser().getId();
        this.totalPrice = order.getTotalPrice();
    }

    @Override
    public String key() {
        return "OrderSuccess-"+UUID.randomUUID();
    }

    public HashMap<Long, BigDecimal> getProductMap() {
        return productMap;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
