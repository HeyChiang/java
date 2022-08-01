package com.ddd.product.application.eventhandler;

import com.ddd.infracore.event.DomainEventHandler;
import com.ddd.order.application.dto.BuyProductDto;
import com.ddd.order.domain.entity.Order;
import com.ddd.order.domain.event.OrderSuccessEvent;
import com.ddd.product.infrastructure.mapper.ProductMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        Order order = (Order) event.getSource();
        for (BuyProductDto buyProductDto : order.getProductList()) {
            Integer integer = productMapper.updateStock(buyProductDto.getId(), buyProductDto.getBuyNum());
            if (integer <= 0) {
                throw new RuntimeException(buyProductDto.getId() + "扣除库存失败");
            }
        }
        System.out.println("我是商品扣库存" + order.getOrderId());
    }
}
