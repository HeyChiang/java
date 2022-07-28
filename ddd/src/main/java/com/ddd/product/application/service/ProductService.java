package com.ddd.product.application.service;

import com.ddd.product.application.dto.ProductDto;

import java.util.List;

/**
 * 商品服务操作，可以被外部的领域使用
 *
 * @author JiangHao
 */
public interface ProductService {

    List<ProductDto> selectAll(List<Long> ids);
}
