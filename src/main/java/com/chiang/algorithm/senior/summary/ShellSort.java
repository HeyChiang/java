package com.chiang.algorithm.senior.summary;

import com.chiang.algorithm.SortHelper;

/**
 * 希尔排序，每次是长度/2的方式把最远的数值拉近，降低数值对比交换的次数
 *
 * @author JiangHao
 */
public class ShellSort {

    private ShellSort() {
    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[]{49,44,94,60,38};
        SortHelper.print("源数据：", integers);
        new ShellSort().sort2(integers);
        SortHelper.print(integers);
    }

    /**
     * 希尔排序（总会有一个没有排序正确，在）
     */
    public void sort2(Integer[] arr) {
        int h = arr.length / 2;

        // 步长需要大于0
        while (h > 0) {
            System.out.println("h："+h);

            // h 切数据为N段，就要执行N次
            for (int start = 0; start < h; start++) {

                // 数据的开始步长，确保
                for (int i = start; i < arr.length; i += h) {

                    // 记录当前要对比的数
                    Integer temp = arr[i];
                    int j;

                    // 如果temp < 左边的，就和左边的比较交换
                    for (j = i; j + h <arr.length && temp.compareTo(arr[j + h]) > 0; j += h) {
                        arr[j] = arr[j + h];
                    }
                    // 最后的一个j位置是上一个j-h，所以要用temp替换
                    arr[j] = temp;
                }
                SortHelper.print("排列：",arr);
            }

            // 数据对比间距每次缩小两倍
            h /= 2;
        }
    }


    /**
     * 希尔排序
     */
    public void sort(Integer[] arr) {
        int h = arr.length / 2;

        // 步长需要大于0
        while (h > 0) {

            // h 切数据为N段，就要执行N次
            for (int start = 0; start < h; start++) {

                // 数据的开始步长，确保步长的数值都被循环对比了一次
                for (int i = start + h; i < arr.length; i += h) {

                    // 记录当前要对比的数
                    Integer temp = arr[i];
                    int j;

                    // 如果temp < 左值，就把左值放在j的位置，相当于右移
                    for (j = i; j - h >= 0 && temp.compareTo(arr[j - h]) < 0; j -= h) {
                        arr[j] = arr[j - h];
                    }
                    // 最后的一个j位置是上一个j-h，所以要用temp替换
                    arr[j] = temp;
                }
            }

            // 数据对比间距每次缩小两倍
            h /= 2;
        }
    }

}
