package com.example.demo.config;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基于Redis的Redisson分布式可重入锁RLock
 * Created by Chiang
 */
@Controller
@RestController
@RequestMapping("/redisson")
public class RedissonReentrantLock {

    private Integer stock = 100;

    /**
     * for循环的次数
     */
    private static final Integer FOR_TIMES = 1000;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * @param stock 当前库存数量，默认100
     * @param mode 1=开启加锁方式扣库存，其他直接扣库存
     */
    @GetMapping("/reentrant")
    public void reentrantTest(@RequestParam(value = "stock",defaultValue = "100") Integer stock,@RequestParam(value = "mode",defaultValue = "1") Byte mode) {
        this.stock = stock;
        //使用线程池模拟并发，看分布式锁有没有问题
        ExecutorService executorService = Executors.newFixedThreadPool(500);

        if(mode == 1){
            //调用加锁方法
            System.out.println("------------加锁扣库存------------");
        }else {
            //不加锁执行
            System.out.println("------------不加锁直接扣库存------------");
        }

        for (int i = 0; i <= FOR_TIMES; i++) {
            executorService.execute(() -> {
                try {

                    if(mode == 1){
                        //调用加锁方法
                        reduceStockRessionLock(i);
                    }else {
                        //不加锁执行
                        reduceStock();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @GetMapping("/getStock")
    public Integer getStock(){
        return stock;
    }

    /**
     * 加锁情况
     */
    private void reduceStockRessionLock(Integer i) throws InterruptedException {
        //获取锁（可重入锁）
        RLock lock = redissonClient.getLock("anyLock");

        //加锁
        lock.lock();

        //减去库存
        reduceStock();

        //释放锁
        lock.unlock();
    }

    /**
     * 减去库存
     */
    private void reduceStock() throws InterruptedException {
        //业务操作
        if (stock > 0) {
            //订单处理时间100毫秒秒
            Thread.sleep(100);

            stock--;
            System.out.println("当前库存剩余：" + stock);
        }
    }

}