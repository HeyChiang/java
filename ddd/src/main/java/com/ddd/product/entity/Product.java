package com.ddd.product.entity;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * 商品领域
 *
 * @author JiangHao
 */
@Data
public class Product {

    private int id;
    private String title;
    private BigDecimal price;
    private BigDecimal stock;

    public boolean save(@Validated Product product){

        return false;
    }
}
