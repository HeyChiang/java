package com.chiang.algorithm.merge;

import com.chiang.algorithm.foundation.SortHelper;

import java.util.Arrays;

public class MyMergeSort {

    public static void main(String[] args) {
        Integer[] arrays = new Integer[]{98, 3, 4, 6, 5, 1, 2};
        SortHelper.print("原始数组：",arrays);
        MyMergeSort.sort(arrays, 0, arrays.length);
        SortHelper.print("排序后：",arrays);
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

        SortHelper.print("merge 位置：左"+l+" 右"+r+" 数组：",temp);

        int i = l, j = mid;

        // 从做导游遍历整个数组
        for (int k = l; k < r; k++) {
            try{
                if (i > mid) {
                    // 左边的数组已经完成排序
                    array[k] = temp[j - l];
                    j++;
                } else if (j >= r) {
                    // 右边的数组已完成排序
                    array[k] = temp[i - l];
                    i++;
                } else if ( temp[i - l].compareTo(temp[j - l]) <= 0) {
                    // 左边小于或等于右边的数值
                    array[k] = temp[i - l];
                    i++;
                } else {
                    // 右边大于左边的数值
                    array[k] = temp[j - l];
                    j++;
                }
            }catch (Exception e){
                System.out.println("异常 位置"+l+","+mid+","+j+" ，运算："+(i-l)+","+(j-l)+","+temp.length);
                e.printStackTrace();
                break;
            }

        }

        SortHelper.print("merge 成功："+l+","+r+" 数组：",array,l,r);
        System.out.println();
    }
}
