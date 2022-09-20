package com.chiang.shardingsphere;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    void query(){
        // 做了表绑定以后，就不回出现笛卡尔积的情况
        QueryWrapper<OrderItem> orderItemQueryWrapper = new QueryWrapper<>();
        orderItemQueryWrapper.eq("id",1);
        orderItemQueryWrapper.eq("user_id",1);
        orderItemMapper.selectList(orderItemQueryWrapper);
    }

    @Test
    void insert() {
        Random random = new Random();
        Order order = new Order();
        OrderItem orderItem = new OrderItem();

        int index = 50;
        for (long i = index; i < index+10; i++) {
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
