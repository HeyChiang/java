package com.ddd.product.application.repository;

import com.ddd.product.domain.entity.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品资源获取类，使用在领域内部
 *
 * @author JiangHao
 */
public interface ProductRepository {

    /**
     * 查询所有的商品
     *
     * @return 商品领域对象
     */
    List<Product> selectAll(List<Long> ids);
}
