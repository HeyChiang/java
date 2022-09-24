package chiang.algorithm.recursion.quick;

import chiang.algorithm.SortHelper;

/**
 * 三路快速排序，解决相同数值的数组排序问题
 */
public class Quick3Sort {
    public static void main(String[] args) {
        quickSortArr();
    }

    private static void quickSortArr() {
        Integer[] arr = new Integer[]{3,5,7,6,2};
        SortHelper.print("-----原始", arr);
        sort(arr, 0, arr.length);
        SortHelper.print("-----结果", arr);
    }

    /**
     * 对数组进行排序，不断的将数组切分为小块的左右两个，直到无法切分。
     *   1. 最小的数组始终在左边被排序
     *   2. 最大的数组始终在右边被排序
     *   3. 相同的数组放在中间不管
     */
    public static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        if (l >= r) return;

        // arr[l+1...lt-1] < v , arr[lt...gt-1] =v ,arr[gt...r] > v
        int lt = l, i = l + 1, gt = r;
        while (i < gt) {
            // i:不断向前遍历的参数
            if (arr[i].compareTo(arr[l]) < 0) {
                // lt：小于v的参数都要被放到lt位置
                lt++;
                SortHelper.swap(arr, i, lt);
                i++;
            } else if (arr[i].compareTo(arr[l]) > 0) {
                // gt：大于v的参数都要被放到gt位置
                gt--;
                SortHelper.swap(arr, gt, i);
            } else {
                i++;
            }
        }

        // 最后的l放到中间去
        SortHelper.swap(arr, l, lt);

        SortHelper.print("排序", arr,l,r);

        // 对小的数组进行排序
        sort(arr, l, lt );

        // 对大的数组进行排序
        sort(arr, gt, r);
    }


}
