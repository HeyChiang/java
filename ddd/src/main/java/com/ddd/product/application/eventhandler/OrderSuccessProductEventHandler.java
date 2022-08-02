package com.ddd.product.application.eventhandler;

import com.alibaba.druid.support.json.JSONUtils;
import com.ddd.infracore.event.DomainEventHandler;
import com.ddd.order.domain.entity.BuyProduct;
import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.event.OrderSuccessEvent;
import com.ddd.product.infrastructure.mapper.ProductMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 订单成功事件处理
 *
 * @author JiangHao
 */
@Component
@Setter(onMethod_ = @Autowired)
public class OrderSuccessProductEventHandler implements DomainEventHandler<OrderSuccessEvent> {

    private ProductMapper productMapper;

    @Override
    public void onApplicationEvent(OrderSuccessEvent event) {
        HashMap<Long, BigDecimal> productMap = event.getProductMap();
        productMap.forEach((id,buyStock)->{
            Integer integer = productMapper.updateStock(id, buyStock);
            if (integer <= 0) {
                throw new RuntimeException(id + "扣除库存失败");
            }
        });

        System.out.println(productMap.size()+"个商品扣库存!");
    }
}
