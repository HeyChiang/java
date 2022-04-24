package com.chiang.algorithm.merge;

import java.util.Arrays;

public class MyMergeSort {

    public static void main(String[] args) {
        Integer[] arrays = new Integer[]{98, 3, 4, 6, 5, 1, 2};
        MyMergeSort.sort(arrays, 0, arrays.length);
        for (Integer array : arrays) {
            System.out.println(array);
        }
    }

    private static <E extends Comparable<E>> void sort(E[] array, int l, int r) {
        if ((l+1) == r) {
            return;
        }

        int mid = l + (r - l) / 2;
        sort(array, l, mid);
        sort(array, mid, r);
        merge(array, l, mid, r);
    }

    private static <E extends Comparable<E>> void merge(E[] array, int l, int mid, int r) {
        E[] temp = Arrays.copyOfRange(array, l, r);

        int i = l, j = mid;

        // 从做导游遍历整个数组
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                // 左边的数组已经完成排序
                array[k] = temp[j - l];
                j++;
            } else if (j > r) {
                // 右边的数组已完成排序
                array[k] = temp[i - l];
                i++;
            } else if (temp[i - l].compareTo(temp[j - l]) <= 0) {
                // 左边小于或等于右边的数值
                array[k] = temp[i - l];
                i++;
            } else {
                // 右边大于左边的数值
                array[k] = temp[j - l];
                j++;
            }
        }
    }
}
