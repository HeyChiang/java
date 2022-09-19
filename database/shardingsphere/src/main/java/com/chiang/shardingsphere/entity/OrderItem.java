package com.chiang.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("order_item")
public class OrderItem {

    private Long id;
    private Long orderId;
    private Date createTime;
    private Long userId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}