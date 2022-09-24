package chiang.java.math;

import java.math.BigDecimal;

public class MathDemo {
    public static void main(String[] args) {
        BigDecimal bigA = new BigDecimal("0.0");
        BigDecimal bigB = new BigDecimal("0.00");

        System.out.println(bigA.compareTo(bigB));
        System.out.println(toFixTwo(bigA));
    }

    /**
     * 只保留2位小数，其他的丢弃掉
     */
    private static BigDecimal toFixTwo(BigDecimal bigDecimal) {
        assert bigDecimal != null;

        String s = bigDecimal.toString();
        int i = s.indexOf(".");
        if (i == -1) {
            return bigDecimal;
        }

        String decimal = s.substring(i, s.length() - 1);
        if(decimal.length() > 2){
            return new BigDecimal(s.substring(0, i + 3));
        }

        return bigDecimal;
    }
}
