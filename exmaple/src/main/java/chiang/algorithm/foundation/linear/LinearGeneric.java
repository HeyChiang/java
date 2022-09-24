package chiang.algorithm.foundation.linear;

import chiang.algorithm.SortHelper;

/**
 * 泛型的线性查找
 *
 * @author JiangHao at 1/15/2022 6:57 PM
 */
public class LinearGeneric {

    /**
     * 通过Java的泛型去线性查找
     */
    public static <E> E search(E[] e, E target) {
        for (E value : e) {
            if (value.equals(target)) {
                return value;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Integer[] testTime = new Integer[]{1000000, 10000000};

        for (Integer integer : testTime) {
            Integer[] array = SortHelper.generateArray(integer);
            long startTime = System.currentTimeMillis();
            search(array, -1);
            double useTime = (System.currentTimeMillis() - startTime)/1000.0;
            System.out.println("run：" + integer + " time：" + useTime +"s");
        }
    }
}
