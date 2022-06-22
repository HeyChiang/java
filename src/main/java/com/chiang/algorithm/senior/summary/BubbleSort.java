package com.chiang.algorithm.senior.summary;

import com.chiang.algorithm.SortHelper;

/**
 * 冒泡排序
 *
 * @author Chiang
 */
public class BubbleSort {
    public static void main(String[] args) {
        Integer[] integers = SortHelper.generateArray(10);
        SortHelper.print("原始： ", integers);

        // 确定循环次数，因为是相邻两个数，最后一个数不需要对比，所以i+1
        for (int i = 0; i + 1 < integers.length; i++) {

            //
            for (int j = 0; j < integers.length - i - 1; j++) {
                if (integers[j] > integers[j + 1]) {
                    SortHelper.swap(integers, j, j + 1);
                }
            }
        }

        SortHelper.print(integers);
    }
}
