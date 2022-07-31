package com.ddd.product.infrastructure.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品数据访问对象
 *
 * @author JiangHao
 */
@Data
@TableName("product")
public class ProductDO {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private BigDecimal price;
    private BigDecimal stock;
}
