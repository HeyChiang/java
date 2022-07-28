package com.ddd.order.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddd.order.infrastructure.dataobject.OrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单数据访问类
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderDO> {
}
