package com.ddd.order.adapter.vo;

import com.ddd.order.domain.entity.Order;
import lombok.Data;

/**
 * 订单数据视图类
 *
 * @author JiangHao
 */
@Data
public class OrderVO {
    private Long orderId;

    public static OrderVO copyFrom(Order order){
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId(order.getOrderId());
        return orderVO;
    }
}
