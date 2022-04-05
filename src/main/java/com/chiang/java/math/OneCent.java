package com.chiang.java.math;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 一份钱测试Demo
 * @author JiangHao at 2022/4/4 16:35
 */
public class OneCent {

    public static void main(String[] args) {
        // 优惠券金额
        BigDecimal coupon = new BigDecimal(3);
       testCoupon(coupon);
    }

    private static void testCoupon(BigDecimal coupon) {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("香蕉",new BigDecimal("5.2"),null));
        productList.add(new Product("苹果",new BigDecimal("2.3"),null));
        productList.add(new Product("辣椒",new BigDecimal("2.2"),null));

        BigDecimal productMoneySum = getProductMoneySum(productList,1);

        for (Product product : productList) {
            BigDecimal ratio = product.getMoney().divide(productMoneySum,2, RoundingMode.HALF_UP);
            BigDecimal productCoupon = coupon.multiply(ratio);
            product.couponMoney = product.money.subtract(productCoupon);
            System.out.println("商品价格:"+product.getMoney() +" , 商品优惠："+product.getCouponMoney());
        }

        BigDecimal productCouponMoneySum = getProductMoneySum(productList, 2);

        System.out.println("原总价："+productMoneySum+" 优惠券："+coupon+" 优惠总价："+productCouponMoneySum);

        fixOneCent(coupon,productList,productMoneySum,productCouponMoneySum);
    }

    /**
     * 修复金额
     */
    private static void fixOneCent(BigDecimal coupon, List<Product> productList,BigDecimal productMoneySum,BigDecimal productCouponMoneySum) {
        BigDecimal sumProductCoupon = productMoneySum.subtract(productCouponMoneySum);
        BigDecimal errorMoney = coupon.subtract(sumProductCoupon);

        int compareTo = errorMoney.compareTo(BigDecimal.ZERO);
        if(compareTo > 0){
            productList.get(0).couponMoney = productList.get(0).couponMoney.add(errorMoney);
        }else if(compareTo < 0){
            productList.get(0).couponMoney = productList.get(0).couponMoney.subtract(errorMoney.negate());
        }

        productCouponMoneySum = getProductMoneySum(productList, 2);

        System.out.println("修复后:");
        System.out.println("原总价："+productMoneySum+" 优惠券："+coupon+" 优惠总价："+productCouponMoneySum);
    }

    private static BigDecimal getProductMoneySum(List<Product> productList,int type) {
        BigDecimal moneySum = BigDecimal.ZERO;
        for (Product product : productList) {
            if(type==1){
                moneySum = moneySum.add(product.money);
            }else if(type==2){
                moneySum = moneySum.add(product.couponMoney);
            }

        }

        return moneySum;
    }

    @Data
    @AllArgsConstructor
    static class Product{
        String title;
        BigDecimal money;
        /**
         * 优惠后金额
         */
        BigDecimal couponMoney;
    }
}
