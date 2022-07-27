package com.ddd.order.application.dto;

import com.ddd.order.application.pram.ProductParam;
import lombok.Data;

import java.util.List;

/**
 * 订单数据传输对象
 *
 * @author JiangHao
 */
@Data
public class OrderDto {
    private Long userId;
    private List<ProductParam> productIds;
}
