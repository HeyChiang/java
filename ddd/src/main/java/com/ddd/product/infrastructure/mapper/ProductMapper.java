package com.ddd.product.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddd.product.application.dto.ProductDto;
import com.ddd.product.infrastructure.dataobject.ProductDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户数据访问类
 *
 * @author JiangHao
 */
public interface ProductMapper extends BaseMapper<ProductDO> {

    /**
     * 通过商品Id数组查询商品
     */
    @Select("select * from product where id in " +
            "<foreach collection=\"ids\" item=\"id1\" open=\"(\" separator=\",\" close=\")\" > " +
            "   #{id1} " +
            " </foreach>")
    List<ProductDto> allProduct(@Param("ids") List<Long> ids);
}
