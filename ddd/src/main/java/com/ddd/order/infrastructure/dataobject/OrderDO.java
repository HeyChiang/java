package com.ddd.order.infrastructure.dataobject;

import com.ddd.order.domain.enums.OrderStatusEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单数据访问类
 */
@Data
public class OrderDO {
    private Long id;
    private BigDecimal totalPrice;
    private String productInfo;
    private String couponInfo;
    private Long userId;
    private String userName;
    private String userPhone;
    private String address;
    private OrderStatusEnum orderStatus;
}
