package com.chiang.algorithm.linear;

/**
 * 泛型
 *
 * @author JiangHao at 1/15/2022 6:57 PM
 */
public class Generic {

    /**
     * 通过Java的泛型去线性查找
     */
    public static <E> E search(E[] e, E target) {
        for (int i = 0; i < e.length; i++) {
            if (e[i].equals(target)) {
                return e[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Integer[] array = {1, 5, 3, 2, 7, 55, 3};
        System.out.println(search(array, 3));
    }
}
