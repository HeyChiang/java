package com.chiang.algorithm.foundation;

import java.util.Random;

public class SortHelper {



    /**
     * 生成数组
     */
    public static Integer[] generateArray(Integer num) {
        Random random = new Random();
        Integer[] array = new Integer[num];
        for (int i = 0; i < num; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }
}
