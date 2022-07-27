package com.ddd.product.application.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddd.infracore.tools.ListBeanCopy;
import com.ddd.product.application.dto.ProductDto;
import com.ddd.product.application.service.ProductService;
import com.ddd.product.infrastructure.dataobject.ProductDO;
import com.ddd.product.infrastructure.mapper.ProductMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
    public List<ProductDto> selectAll() {

        List<ProductDto> dtoList = productMapper.selectAll();

        return ListBeanCopy.copy(dtoList, ProductDto::new);
    }

    @Override
    public List<ProductDto> selectAll(Long[] ids) {

        return null;
    }
}
