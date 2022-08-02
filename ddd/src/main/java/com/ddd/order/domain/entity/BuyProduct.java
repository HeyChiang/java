package com.ddd.order.domain.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 用户数据传输类
 *
 * @author JiangHao
 */
@Data
public class BuyProduct {
    private Long id;
    private String title;
    private BigDecimal price;
    private BigDecimal stock;

    /**
     * 购买商品的数量
     */
    private BigDecimal buyNum;
}
