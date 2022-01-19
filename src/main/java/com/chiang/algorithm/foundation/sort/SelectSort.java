package com.chiang.algorithm.foundation.sort;

import com.chiang.algorithm.foundation.linear.LinearGeneric;

/**
 * 选择排序算法，O(n^2)的时间复杂度相差百倍
 * @author Chiang
 */
public class SelectSort {

    public static void main(String[] args) {

        // 数据量相差10倍，10^2就是相差百倍的运行速度
        Integer[] testTime = new Integer[]{10000, 100000};

        for (int k = 0; k < testTime.length; k++) {
            Integer[] array = LinearGeneric.generateArray(testTime[k]);
            long startTime = System.currentTimeMillis();

            // array[0..i)是已排序的，array[i..n)是未排序的
            for (int i = 0; i < array.length; i++) {
                for (int j = i+1; j < array.length; j++) {
                    if(array[i] > array[j]){
                        swap(i,j,array);
                    }
                }
            }
            double useTime = (System.currentTimeMillis() - startTime)/1000.0;
            System.out.println("run：" + testTime[k] + " time：" + useTime +"s");
        }


    }

    /**
     * 交换数组内参数的位置
     */
    private static void swap(int i, int j, Integer[] array) {
        int temp  = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
