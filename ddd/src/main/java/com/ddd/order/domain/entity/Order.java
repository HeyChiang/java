package com.ddd.order.domain.entity;

import com.ddd.infracore.event.DomainEventBus;
import com.ddd.order.domain.enums.OrderStatusEnum;
import com.ddd.order.domain.event.OrderSuccessEvent;
import com.ddd.product.application.dto.ProductDto;
import com.ddd.user.application.dto.UserDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * 订单领域模型
 *
 * @author JiangHao
 */
@Data
@Builder
public class Order {
    private Long orderId;
    private List<ProductDto> productList;
    private UserDto user;
    private OrderStatusEnum orderStatus;
    private DomainEventBus eventBus;


    /**
     * 创建订单的业务逻辑
     */
    public void create(){
        // 做好业务判断的逻辑
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
