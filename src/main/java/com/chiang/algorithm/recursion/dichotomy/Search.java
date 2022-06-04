package com.chiang.algorithm.recursion.dichotomy;

/**
 * 基本的二分查找法
 */
public class Search {
    public static void main(String[] args) {
        Integer[] integers = new Integer[]{1, 3, 4, 5, 6, 8};
        System.out.println("索引位置：" + search(integers, 5));
    }

    /**
     * 寻找参数在数组中的位置
     *
     * @param e 数组对象
     * @param target 搜索数组对象中，本参数所在的位置
     * @return 返回参数所在的位置，如果没有则返回 l=0
     */
    public static <E extends Comparable<E>> int search(E[] e, E target) {
        int l = 0, r = e.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (e[mid].compareTo(target) == 0) {
                // 找到了就直接返回了
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

        return l;
    }
}
