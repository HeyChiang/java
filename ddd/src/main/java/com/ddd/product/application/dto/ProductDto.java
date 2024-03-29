package com.ddd.product.application.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 用户数据传输类
 *
 * @author JiangHao
 */
@Data
public class ProductDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private BigDecimal stock;
}
