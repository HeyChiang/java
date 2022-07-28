package com.ddd.order.domain.entity;

import com.ddd.infracore.event.DomainEventBus;
import com.ddd.order.application.dto.BuyProductDto;
import com.ddd.order.domain.enums.OrderStatusEnum;
import com.ddd.order.domain.event.OrderSuccessEvent;
import com.ddd.user.application.dto.UserDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * 订单领域模型，聚合跟是赶紧
 *
 * @author JiangHao
 */
@Data
@Builder
public class Order {
    private Long orderId;
    private List<BuyProductDto> productList;
    private UserDto user;
    private OrderStatusEnum orderStatus;
    private DomainEventBus eventBus;
    private BigDecimal totalPrice;


    /**
     * 创建订单的业务逻辑
     */
    public void create(){
        // 检查库存，计算总价
        for (BuyProductDto buyProductDto : this.productList) {
            if(buyProductDto.getBuyNum().compareTo(buyProductDto.getStock()) > 0){
                throw new RuntimeException(buyProductDto.getTitle()+"库存不足，目前库存："+buyProductDto.getStock());
            }
            totalPrice = totalPrice.add(buyProductDto.getPrice());
        }

        orderStatus = OrderStatusEnum.WAIT_PAY;
        eventBus.post(new OrderSuccessEvent(this));
    }

    public Order build(){
        if(CollectionUtils.isEmpty(productList)){
            throw new RuntimeException("商品集合不能为空");
        }
        if(Objects.isNull(user)){
            throw new RuntimeException("用户数据不能为空");
        }
        return this;
    }

}