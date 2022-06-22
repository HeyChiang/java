package com.chiang.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SortHelper {


    /**
     * 生成数组
     */
    public static Integer[] generateArray(Integer num) {
        Random random = new Random();
        Integer[] array = new Integer[num];
        for (int i = 0; i < num; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }

    public static int[] generateArrayInt(int num) {
        Random random = new Random();
        int[] array = new int[num];
        for (int i = 0; i < num; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }


    /**
     * 交换 i 与 j 的位置
     */
    public static <E> void swap(E[] arr, int i, int j) {
        E e = arr[j];
        arr[j] = arr[i];
        arr[i] = e;
    }

    public static <E> void print(String str, E[] array) {
        print(str, array, 0, array.length);
    }

    public static <E> void print( E[] array) {
        print(null, array, 0, array.length);
    }

    public static void print(String str, int[] array) {
        Integer[] integers = Arrays.stream(array).map(Integer::new).boxed().toArray(Integer[]::new);
        print(str, integers, 0, array.length);
    }

    public static <E> void print(String str, E[] array, int l, int r) {
        print(str, array, l, r, 0);
    }

    /**
     * @param offset 补偿，有些运算打印要比数组多1
     */
    public static <E> void print(String str, E[] array, int l, int r, int offset) {

        if (Objects.nonNull(str)) {
            System.out.print(str + " l:" + l + " r:" + (r + offset) + " 内容：");
        }

        StringBuilder buffer = new StringBuilder();

        for (int i = l; i < r + offset; i++) {
            buffer.append(array[i]);
            buffer.append(",");
        }
        int last = buffer.lastIndexOf(",");
        if (last!= -1 && last == buffer.length() - 1) {
            buffer.delete(last, buffer.length());
        }
        System.out.println(buffer);
    }



    /**
     * 生成固定的数组，让快速排序规定从中间排序的算法退化
     */
    public static Integer[] generateSpecialArray(int n) {

        Integer[] arr = new Integer[n];
        generateSpecialArray(arr, 0, arr.length - 1, 0);
        return arr;
    }

    /**
     * 生成退化数组，让中间始终是最小的数，导致快排算法的递归深度为O（n）
     */
    private static void generateSpecialArray(Integer[] arr, int l, int r, int value) {

        if (l > r) return;

        // 让中间的数为最小的
        int mid = (l + r) / 2;
        arr[mid] = value;


        SortHelper.print(null,arr);
        SortHelper.print("前：",arr,l,r,1);
        System.out.println();

        // 把最小的数字放在最左边，不这样做在下次递归时，因为整数除法可能被覆盖掉
        swap(arr, l, mid);
        // 最小的数值已经移动到最左，就不也许被覆盖了，左边+1
        generateSpecialArray(arr, l + 1, r, value + 1);
        // 把最小的数字放回到中间
        swap(arr, l, mid);

        SortHelper.print(null,arr);
        SortHelper.print("后：",arr,l,r,1);
        System.out.println();
    }
}
