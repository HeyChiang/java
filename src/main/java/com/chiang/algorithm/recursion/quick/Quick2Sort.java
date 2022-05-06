package com.chiang.algorithm.recursion.quick;

import com.chiang.algorithm.SortHelper;

import static com.chiang.algorithm.SortHelper.swap;

/**
 * 双路快速排序
 */
public class Quick2Sort {
    public static void main(String[] args) {
        quickSortArr();
    }

    private static void quickSortArr() {
        Integer[] arr = SortHelper.generateArray(10);
//        Integer[] arr = {4, 2, 1};
        sort(arr, 0, arr.length - 1);
        SortHelper.print("结果", arr);
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
     *  双路排序，时间复杂度是O(n)，一个数组只会被遍历一次
     */
    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {

        int j = r;
        int i = l + 1;

        while (true) {
            while (i < j && arr[i].compareTo(arr[l]) < 0) {
                i++;
            }

            // 在上的一个while循环当中，i的索引可能会直接=j，所以这里必须用>=
            // 这样就可以保证 while循环外层的swap，可以与arr[l]位置正确交换
            while (j >= i && arr[j].compareTo(arr[l]) > 0) {
                j--;
            }

            if (i >= j) {
                break;
            }

            // 交换以后，索引要+1指向下一个数值
            swap(arr, i, j);
            i++;
            j--;
        }

        swap(arr, l, j);

        // {5, 4, 2, 1, 31}
        SortHelper.print("排序：", arr, l, r, 1);
        System.out.println();
        return j;
    }


}
