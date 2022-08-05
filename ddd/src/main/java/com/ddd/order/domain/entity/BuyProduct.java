package com.ddd.order.domain.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 用户数据传输类
 *
 * @author JiangHao
 */
@Data
public class BuyProduct {
    private Long id;
    private String title;
    private BigDecimal price;
    private BigDecimal stock;

    /**
     * 购买商品的数量
     */
    private BigDecimal buyNum;

    /**
     * 单次购买限制
     */
    private BigDecimal buyLimit;


    /**
     * 判断是否可以购买商品
     *
     * @return 如果不能购买商品，则返回字符串提示，否则返回null
     */
    public String canBuy(){

        // 判断库存是否足够
        if(stock.compareTo(buyNum) < 0 ){
            return "商品库存不足："+this.getTitle();
        }

        // 本次购买的数量大于购买的限制
        if(buyNum.compareTo(buyLimit) <= 0){
            return "购买商品的数量超过限制："+buyLimit;
        }

        return null;
    }
}
