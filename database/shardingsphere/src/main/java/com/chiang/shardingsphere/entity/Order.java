package com.chiang.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 对于ShardingSphere框架来说，MySQL保留关键字不要用。
 * 例如表名为order就会导致插入异常出现。
 */
@TableName("my_order")
public class Order {

    private Long id;
    private Date createTime;
    private Long userId;

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