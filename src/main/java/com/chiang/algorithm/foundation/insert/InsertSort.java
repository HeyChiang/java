package com.chiang.algorithm.foundation.insert;

import com.chiang.algorithm.foundation.SortHelper;

import java.util.Arrays;

/**
 * 插入排序法和选择排序法差别在于，选择排序在一轮循环以后，第一个一定是数据里最小的，
 * 而插入则排序不会超过i的索引大小。
 * @author Chiang
 */
public class InsertSort {

    public static void main(String[] args) {
        Integer[] testTime = new Integer[]{10000,100000};

        for (int k = 0; k < testTime.length; k++) {
            Integer[] array = SortHelper.generateArray(testTime[k]);
            long startTime = System.currentTimeMillis();

            // array[0..i)是已排序的，array[i..n)是未排序的
            sortNormal(array);
//            sortOptimize(array);
            double useTime = (System.currentTimeMillis() - startTime)/1000.0;
            System.out.println("run：" + testTime[k] + " time：" + useTime +"s");
        }
    }

    /**
     * 优化，减少不断交换的数组数据的寻址操作，赋值只有一次
     */
    public static void sortOptimize(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            int temp = array[i];
            int j;

            // 特点： >temp时候会提前终止循环，而普通排序则会整个循环遍历完成
            for (j = i; j-1 >= 0 && array[j-1] > temp; j--) {
                array[j] = array[j-1];
            }
            array[j] = temp;
        }
    }

    /**
     * 普通排序
     */
    private static void sortNormal(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j-1 >= 0 ; j--) {
                // 一定会把j遍历完成
                if(array[i].compareTo(array[j]) > 0){
                    swap(i,j,array);
                }
            }
        }
    }

    /**
     * 交换数组内参数的位置，每次交换有三次操作
     */
    private static void swap(int i, int j, Integer[] array) {
        int temp  = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
