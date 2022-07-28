package com.ddd.order.adapter.controller;

import com.ddd.infracore.http.Result;
import com.ddd.order.adapter.vo.OrderVO;
import com.ddd.order.application.dto.OrderDto;
import com.ddd.order.application.service.OrderService;
import com.ddd.order.domain.entity.Order;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单控制器
 *
 * @author jianghao
 */
@RestController
@RequestMapping("/order")
@Setter(onMethod_ = @Autowired)
public class OrderController {

    private OrderService orderService;

    /**
     * 创建订单
     * @param param 订单入参
     * @return 订单对象
     */
    @PutMapping
    public Result<OrderVO> createOrder(@RequestBody OrderDto param){
        Order order = orderService.createOrder(param);
        return Result.success(OrderVO.copyFrom(order));
    }

}
