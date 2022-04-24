package com.chiang.algorithm.foundation;

import java.util.Objects;
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

    public static <E> void print(String str,E[] array){

        if(Objects.nonNull(str)){
            System.out.print(str);
        }

        for (E e : array) {
            System.out.print(e);
            System.out.print(",");
        }

        System.out.println("");
    }
}
