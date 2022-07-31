package com.ddd.product.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddd.product.application.dto.ProductDto;
import com.ddd.product.infrastructure.dataobject.ProductDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
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
    @Select("<script>select * from product where id in " +
            "<foreach item=\"id\" index=\"index\" collection=\"ids\"" +
            "        open=\"(\" separator=\",\" close=\")\">\n" +
            " #{id}\n" +
            "</foreach></script>")
    List<ProductDto> allProduct(@Param("ids") List<Long> ids);

    /**
     * 修改商品的库存
     * @param id 商品ID
     * @param stock 需要扣除的商品库存
     */
    @Update("update product set stock=stock-#{stock} where id=#{id}")
    Integer updateStock(@Param("id") Long id, @Param("stock")BigDecimal stock);
}
