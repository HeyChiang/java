package com.ddd.order.domain.entity;

import com.ddd.infracore.event.DomainEventBus;
import com.ddd.order.application.dto.BuyProductDto;
import com.ddd.order.domain.enums.OrderStatusEnum;
import com.ddd.order.domain.event.OrderSuccessEvent;
import com.ddd.order.infrastructure.dataobject.OrderDO;
import com.ddd.user.application.dto.UserDto;
import kotlin.BuilderInference;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * 订单领域模型，聚合跟是赶紧
 *
 * @author JiangHao
 */
public class Order {

    /**
     * 领域只能通过Builder去创建
     */
    private Order(){}

    private Long orderId;
    private List<BuyProductDto> productList;
    private UserDto user;
    private OrderStatusEnum orderStatus;
    private DomainEventBus eventBus;
    private BigDecimal totalPrice;

    /**
     * 创建订单并支付
     */
    public void create() {
        // 检查库存，计算总价
        totalPrice = BigDecimal.ZERO;
        for (BuyProductDto buyProductDto : this.productList) {
            if (buyProductDto.getBuyNum().compareTo(buyProductDto.getStock()) > 0) {
                throw new RuntimeException(buyProductDto.getTitle() + "库存不足，目前库存：" + buyProductDto.getStock());
            }
            totalPrice = totalPrice.add(buyProductDto.getPrice());
        }

        orderStatus = OrderStatusEnum.WAIT_TAKE;
    }

    public static Builder Builder() {
        return new Builder();
    }
    public static class Builder{
        private Long orderId;
        private List<BuyProductDto> productList;
        private UserDto user;
        private OrderStatusEnum orderStatus;
        private DomainEventBus eventBus;
        private BigDecimal totalPrice;

        public Builder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder productList(List<BuyProductDto> productList) {
            this.productList = productList;
            return this;
        }

        public Builder user(UserDto user) {
            this.user = user;
            return this;
        }

        public Builder orderStatus(OrderStatusEnum orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder eventBus(DomainEventBus eventBus) {
            this.eventBus = eventBus;
            return this;
        }

        public Builder totalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Order build() {

            Order order = new Order();
            order.orderId = this.orderId;
            order.totalPrice = this.totalPrice;
            order.eventBus = this.eventBus;
            order.productList = this.productList;
            order.user = this.user;
            order.orderStatus = this.orderStatus;

            if (CollectionUtils.isEmpty(productList)) {
                throw new RuntimeException("商品集合不能为空");
            }
            if (Objects.isNull(user)) {
                throw new RuntimeException("用户数据不能为空");
            }

            return order;
        }
    }



    /**
     * 转换领域对象为数据对象
     */
    public OrderDO toOrderDO(){
        OrderDO orderDO = new OrderDO();
        orderDO.setAddress(this.user.getAddress());
        orderDO.setUserId(this.user.getId());
        orderDO.setUserPhone(this.user.getPhone());
        orderDO.setUserName(this.user.getUserName());
        orderDO.setTotalPrice(this.totalPrice);
        orderDO.setOrderStatus(this.orderStatus);
        orderDO.setProductInfo(this.productList.toString());
        return orderDO;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public List<BuyProductDto> getProductList() {
        return productList;
    }

    public UserDto getUser() {
        return user;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public DomainEventBus getEventBus() {
        return eventBus;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
