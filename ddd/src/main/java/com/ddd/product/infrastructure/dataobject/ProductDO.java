package com.ddd.product.infrastructure.dataobject;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品数据访问对象
 *
 * @author JiangHao
 */
@Data
public class ProductDO {
    private Long id;
    private String title;
    private BigDecimal price;
    private BigDecimal stock;
}
