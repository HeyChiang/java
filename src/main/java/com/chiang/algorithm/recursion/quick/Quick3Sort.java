package com.chiang.algorithm.recursion.quick;

import com.chiang.algorithm.SortHelper;
import com.chiang.algorithm.foundation.selector.SelectSort;

import static com.chiang.algorithm.SortHelper.swap;

/**
 * 三路快速排序，解决相同数值的数组排序问题
 */
public class Quick3Sort {
    public static void main(String[] args) {
        quickSortArr();
    }

    private static void quickSortArr() {
        Integer[] arr = SortHelper.generateArray(11);

        sort(arr, 0, arr.length);
        SortHelper.print("结果", arr);
    }

    /**
     * 对数组进行排序，
     */
    public static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        if (l >= r) return;

        // arr[l+1...lt-1] < v , arr[lt...gt-1] =v ,arr[gt...r] > v
        int lt = l, i = l + 1, gt = r;
        while (i < gt) {
            // i:不断向前遍历的参数
            if (arr[i].compareTo(arr[l]) < 0) {
                // lt：小于v的参数都要被放到lt位置
                lt++;
                swap(arr, i, lt);
                i++;
            } else if (arr[i].compareTo(arr[l]) > 0) {
                // gt：大于v的参数都要被放到gt位置
                gt--;
                swap(arr, gt, i);
            } else {
                i++;
            }
        }

        // 最后的l放到中间去
        swap(arr, l, lt);

        sort(arr, l, lt );
        sort(arr, gt, r);
    }


}
