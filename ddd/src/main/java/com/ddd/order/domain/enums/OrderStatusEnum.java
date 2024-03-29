package com.ddd.order.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举
 *
 * @author JiangHao
 */
@AllArgsConstructor
public enum OrderStatusEnum {
    /**
     * 订单状态,0=待付款、1=待收货、2=已收货、3=已完成、-1=已取消
     */
    WAIT_PAY(0,"待付款"),
    WAIT_TAKE(1,"待收货"),
    RECEPTION(2,"已收货"),
    FINISHED(3,"已完成"),
    CANCELED(-1,"已取消");

    @EnumValue
    private final Integer code;
    private final String text;

    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
