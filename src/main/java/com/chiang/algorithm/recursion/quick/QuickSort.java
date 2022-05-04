package com.chiang.algorithm.recursion.quick;

import com.chiang.algorithm.foundation.SortHelper;

public class QuickSort {
    public static void main(String[] args) {
        Integer[] arr = {5, 4, 2, 1, 31};
        sort(arr, 0, arr.length - 1);
        SortHelper.print("", arr);
    }

    /**
     * 对数组进行排序，
     */
    public static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        if (l >= r) return;

        int position = partition(arr, l, r);

        // position 不需要排序，所以-1和+1
        sort(arr, l, position - 1);
        sort(arr, position + 1, r);
    }

    /**
     *  2. 循环对比arr[i]，小于该数值的放入j的位置
     */
    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {

        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(arr[l]) < 0) {
                j++;
                swap(arr, i, j);
            }
        }
        swap(arr, l, j);
        return j;
    }

    /**
     * 交换 i 与 j 的位置
     */
    private static <E> void swap(E[] arr, int i, int j) {
        E e = arr[j];
        arr[j] = arr[i];
        arr[i] = e;
    }
}
