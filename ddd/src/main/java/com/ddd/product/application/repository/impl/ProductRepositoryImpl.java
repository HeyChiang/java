package com.ddd.product.application.repository.impl;

import com.ddd.infracore.tools.ListBeanCopy;
import com.ddd.product.application.dto.ProductDto;
import com.ddd.product.application.repository.ProductRepository;
import com.ddd.product.domain.entity.Product;
import com.ddd.product.infrastructure.mapper.ProductMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品资源库
 * @author JiangHao
 */
@Repository
@Setter(onMethod_= @Autowired)
public class ProductRepositoryImpl implements ProductRepository {


    private ProductMapper productMapper;

    @Override
    public List<Product> selectAll(List<Long> ids) {
        List<ProductDto> productList = productMapper.allProduct(ids);
        return ListBeanCopy.copy(productList, Product::new);
    }
}
