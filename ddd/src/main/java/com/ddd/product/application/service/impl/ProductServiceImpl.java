package com.ddd.product.application.service.impl;

import com.ddd.infracore.tools.ListBeanCopy;
import com.ddd.product.application.dto.ProductDto;
import com.ddd.product.application.service.ProductService;
import com.ddd.product.infrastructure.mapper.ProductMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务类
 *
 * @author JiangHao
 */
@Service
@Setter(onMethod_ = {@Autowired})
public class ProductServiceImpl implements ProductService {

    private ProductMapper productMapper;

    @Override
    public List<ProductDto> selectAll(List<Long> ids) {

        List<ProductDto> dtoList = productMapper.allProduct(ids);

        return ListBeanCopy.copy(dtoList, ProductDto::new);
    }
}
