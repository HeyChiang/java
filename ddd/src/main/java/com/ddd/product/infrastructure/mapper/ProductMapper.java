package com.ddd.product.infrastructure.mapper;

import com.ddd.product.application.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户数据访问类
 *
 * @author JiangHao
 */
@Mapper
public interface ProductMapper {
    /**
     * 查询所有的商品
     *
     * @return 商品列表集合
     */
    @Select("select * from product")
    List<ProductDto> selectAll();

    /**
     * 通过商品Id数组查询商品
     */
    @Select("select * from product where id in " +
            "<foreach collection=\"ids\" item=\"id\" open=\"(\" separator=\",\" close=\")\" > " +
            "   #{id} " +
            " </foreach>")
    List<ProductDto> selectAll(Long[] ids);
}
