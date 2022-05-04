package com.chiang.algorithm.recursion.merge;

import com.chiang.algorithm.foundation.SortHelper;

import java.util.Arrays;

/**
 * 不需要每次都创建都 Arrays.copyOfRange 创建新的数组，而是第一次全量创建以后传递到合并方法里
 * 减少内存空间的创建
 */
@SuppressWarnings("all")
public class OptimizeMergeSort {

    private OptimizeMergeSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {

        // 有限开辟一个足够长的空间，避免在递归里反复开辟小块的数组空间
        E[] temp = Arrays.copyOfRange(arr, 0, arr.length);
        sort(arr, 0, arr.length - 1, temp);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, E[] temp) {
        if (l >= r)
            return;

        int mid = l + (r - l) / 2;
        sort(arr, l, mid, temp);
        sort(arr, mid + 1, r, temp);

        // 随机数组存在有序的情况，如果有序的就不需要去排序了
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, l, mid, r, temp);
        }

    }


    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp) {
        int offset = 1;

        // 把 arr 的l起点的元素，拷贝到temp中l的位置，拷贝length个元素
        // 必须要有temp临时数组，否则排序过程中arr数组值被排序覆盖无法完成排序了
        System.arraycopy(arr, l, temp, l, r - l + offset);

        int i = l, j = mid + 1;

        SortHelper.print("左：", temp, l, mid, offset);
        SortHelper.print("右：", temp, mid+1, r, offset);
        SortHelper.print("全：", temp, l, r, offset);


        // 每轮循环为 arr[k] 赋值
        for (int k = l; k <= r; k++) {

            if (i > mid) {
                arr[k] = temp[j];
                j++;
            } else if (j > r) {
                arr[k] = temp[i];
                i++;
            } else if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
        }

        SortHelper.print("合并：", arr, l, r,offset);
        SortHelper.print("原始数组", arr);
        System.out.println();
    }

    public static void main(String[] args) {

        Integer[] arr = {1, 7, 4, 2, 8, 3, 6, 5};
        SortHelper.print("原始:", arr);
        sort(arr);
        SortHelper.print("结果:", arr);
    }
}
