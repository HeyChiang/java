package com.ddd.order.infrastructure.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ddd.order.domain.enums.OrderStatusEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单数据访问类
 */
@Data
@TableName("`order`")
public class OrderDO {

    @TableId(type = IdType.AUTO)
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
