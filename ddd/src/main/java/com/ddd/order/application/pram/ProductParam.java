package com.ddd.order.application.pram;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品信息类
 *
 * @author JiangHao
 */
@Data
public class ProductParam {
    private Long productId;
    /**
     * 购买商品的数量
     */
    private BigDecimal number;
}
