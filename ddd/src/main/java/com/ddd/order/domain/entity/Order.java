package com.ddd.order.domain.entity;

import com.ddd.infracore.event.DomainEventBus;
import com.ddd.order.domain.enums.OrderStatusEnum;
import com.ddd.order.infrastructure.dataobject.OrderDO;
import com.ddd.user.application.dto.UserDto;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    private List<BuyProduct> productList;
    private UserDto user;
    private OrderStatusEnum orderStatus;
    private DomainEventBus eventBus;
    private BigDecimal totalPrice;

    /**
     * 创建订单并支付
     */
    public void create() {
        totalPrice = BigDecimal.ZERO;
        for (BuyProduct buyProduct : this.productList) {

            // 购买限制检查
            String canBuyTip = buyProduct.canBuy();
            if (StringUtils.hasText(canBuyTip)) {
                throw new RuntimeException(canBuyTip);
            }

            // 计算商品总价
            totalPrice = totalPrice.add(buyProduct.getPrice());
        }

        orderStatus = OrderStatusEnum.WAIT_TAKE;
    }

    public static Builder Builder() {
        return new Builder();
    }
    public static class Builder{
        private Long orderId;
        private List<BuyProduct> productList;
        private UserDto user;
        private OrderStatusEnum orderStatus;
        private DomainEventBus eventBus;
        private BigDecimal totalPrice;

        public Builder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder productList(List<BuyProduct> productList) {
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

    public List<BuyProduct> getProductList() {
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
