package com.chiang.java.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Math {
    public static void main(String[] args) {
        BigDecimal bigA = new BigDecimal("0.7");
        BigDecimal bigB = new BigDecimal("0.5");
        BigDecimal bigC = bigA.multiply(bigB);
        System.out.println(bigC);

        System.out.println(bigC.min(bigA));
        System.out.println(bigC.max(bigA));

        BigDecimal bigD = new BigDecimal("0.0");
        System.out.println(bigD.compareTo(BigDecimal.ZERO));

        System.out.println(BigDecimal.ZERO);
        System.out.println(BigDecimal.ONE);
        System.out.println(BigDecimal.TEN);

        double a = 1.1;
        System.out.println(-a);

        BigDecimal bigE = new BigDecimal("1.1");
        System.out.println(bigE.negate());

        BigDecimal price = new BigDecimal("1.5");
        BigDecimal nowPrice = new BigDecimal("2.0");
//        double disPrice = price.subtract(nowPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        double disPrice = price.subtract(nowPrice).setScale(2, RoundingMode.HALF_UP).doubleValue();
        System.out.println(disPrice);
    }
}
