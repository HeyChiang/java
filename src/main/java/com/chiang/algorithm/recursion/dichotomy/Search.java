package com.chiang.algorithm.recursion.dichotomy;

public class Search {
    public static void main(String[] args) {
        Integer[] integers = new Integer[]{1, 3, 4, 5, 6, 8};
        System.out.println("索引位置：" + search(integers, 5));
    }

    public static <E extends Comparable<E>> int search(E[] e, E target) {
        int l = 0, r = e.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (e[mid].compareTo(target) == 0) {
                return mid;
            } else if (e[mid].compareTo(target) < 0) {
                // 这里没有减一，是因为length没有和视频中一样-1
                r = mid;
            } else {
                // mid 已经判断不是相等，所以+1忽略它。刚好+1的时候 l==r 也结束循环
                l = mid + 1;
            }
            System.out.println("mid:" + mid + " l:" + l + " r:" + r);
        }

        return -1;
    }
}
