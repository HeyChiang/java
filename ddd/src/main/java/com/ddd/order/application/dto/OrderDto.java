package com.ddd.order.application.dto;

import lombok.Data;

/**
 * 订单数据传输对象
 *
 * @author JiangHao
 */
@Data
public class OrderDto {
    private Long userId;
    private Long[] productIds;
}
