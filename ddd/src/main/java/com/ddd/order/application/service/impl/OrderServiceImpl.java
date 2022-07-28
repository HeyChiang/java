package com.ddd.order.application.service.impl;

import com.ddd.infracore.event.DomainEventBus;
import com.ddd.infracore.tools.ListBeanCopy;
import com.ddd.order.application.dto.BuyProductDto;
import com.ddd.order.application.dto.OrderDto;
import com.ddd.order.application.pram.ProductParam;
import com.ddd.order.application.repository.OrderRepository;
import com.ddd.order.application.service.OrderService;
import com.ddd.order.domain.entity.Order;
import com.ddd.order.infrastructure.mapper.OrderMapper;
import com.ddd.product.application.dto.ProductDto;
import com.ddd.product.application.service.ProductService;
import com.ddd.user.application.dto.UserDto;
import com.ddd.user.application.service.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单服务类
 *
 * @author JiangHao
 */
@Service
@Setter(onMethod_ = @Autowired)
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ProductService productService;
    private DomainEventBus eventBus;
    private UserService userService;

    @Transactional
    @Override
    public Order createOrder(OrderDto orderDto) {
        UserDto userDto = userService.selectUserById(orderDto.getUserId());
        Order order = Order.builder()
                .productList(getBuyProductDto(orderDto.getProductIds()))
                .user(userDto)
                .eventBus(eventBus)
                .build();
        order.create();
        orderRepository.insert(order);
        return order;
    }

    /**
     * 通过参数获取Order领域对象需要的商品集合
     *
     * @param paramList 参数集合
     * @return 领域对象需要的商品集合
     */
    private List<BuyProductDto> getBuyProductDto(List<ProductParam> paramList) {
        List<Long> productIds = paramList.stream().map(ProductParam::getProductId).collect(Collectors.toList());
        List<ProductDto> productList = productService.selectAll(productIds);
        List<BuyProductDto> productDtoList = ListBeanCopy.copy(productList, BuyProductDto::new);
        for (BuyProductDto buyProductDto : productDtoList) {
            for (ProductParam productParam : paramList) {
                if(productParam.getProductId().equals(buyProductDto.getId())){
                    buyProductDto.setBuyNum(productParam.getNumber());
                }
            }

        }
        return productDtoList;
    }
}
