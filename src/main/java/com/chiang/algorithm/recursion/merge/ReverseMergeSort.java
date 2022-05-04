package com.chiang.algorithm.recursion.merge;

import com.chiang.algorithm.SortHelper;

import java.util.Arrays;

/**
 * 计算出逆序队的个数
 */
@SuppressWarnings("all")
public class ReverseMergeSort {

    private ReverseMergeSort() {
    }

    public static <E extends Comparable<E>> int sort(E[] arr) {

        // 有限开辟一个足够长的空间，避免在递归里反复开辟小块的数组空间
        E[] temp = Arrays.copyOfRange(arr, 0, arr.length);
        return sort(arr, 0, arr.length - 1, temp);
    }

    private static <E extends Comparable<E>> int sort(E[] arr, int l, int r, E[] temp) {
        if (l >= r) {
            return 0;
        }

        int mid = l + (r - l) / 2, res = 0;
        res += sort(arr, l, mid, temp);
        res += sort(arr, mid + 1, r, temp);

        // 随机数组存在有序的情况，如果有序的就不需要去排序了
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            res += merge(arr, l, mid, r, temp);
        }

        return res;
    }


    private static <E extends Comparable<E>> int merge(E[] arr, int l, int mid, int r, E[] temp) {
        int offset = 1;

        // 把 arr 的l起点的元素，拷贝到temp中l的位置，拷贝length个元素
        // 必须要有temp临时数组，否则排序过程中arr数组值被排序覆盖无法完成排序了
        System.arraycopy(arr, l, temp, l, r - l + offset);

        int i = l, j = mid + offset, res = 0;

        SortHelper.print("数组",arr,l,r,offset);

        // 每轮循环为 arr[k] 赋值
        for (int k = l; k <= r; k++) {

            if (i > mid) {
                arr[k] = temp[j];
                j++;
            } else if (j > r) {
                arr[k] = temp[i];
                i++;
            } else if (temp[i].compareTo(temp[j]) > 0) {
                res += mid - i + 1;

                arr[k] = temp[j];
                j++;
            } else {
                arr[k] = temp[i];
                i++;
            }
        }

        return res;
    }

    public static void main(String[] args) {

        Integer[] arr = {3, 1, 2, 0};
        SortHelper.print("原始:", arr);
        System.out.println("个数："+sort(arr));
        SortHelper.print("结果:", arr);
    }
}
