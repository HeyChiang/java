package com.chiang.shardingsphere;

import com.chiang.shardingsphere.entity.Order;
import com.chiang.shardingsphere.entity.OrderItem;
import com.chiang.shardingsphere.mapper.OrderItemMapper;
import com.chiang.shardingsphere.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Random;

@SpringBootTest
class ShardingSphereApplicationTests {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;

    @Test
    void contextLoads() {
        Random random = new Random();
        Order order = new Order();
        OrderItem orderItem = new OrderItem();

        for (long i = 0; i < 10; i++) {
            long userId = random.nextInt(1000);
            long orderItemId = random.nextInt(10000);

            order.setId(i);
            order.setUserId(userId);
            int insert = orderMapper.insert(order);

            orderItem.setOrderId(i);
            orderItem.setUserId(userId);
            orderItem.setId(orderItemId);
            int itemInsert = orderItemMapper.insert(orderItem);

            System.out.println("orderInsert:"+insert + " OrderItemInsert:"+itemInsert);
        }
    }

}
