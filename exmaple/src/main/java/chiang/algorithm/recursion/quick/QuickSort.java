package chiang.algorithm.recursion.quick;

import chiang.algorithm.SortHelper;

import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        Integer[] integers = SortHelper.generateSpecialArray(5);
        SortHelper.print("退化数组", integers);

//        quickSortArr();
    }

    private static void quickSortArr() {
        Integer[] arr = {5, 4, 2, 1, 31};
        sort(arr, 0, arr.length - 1);
        SortHelper.print("", arr);
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
     * 遍历数组，将小于arr[l]的放在左边，>=的放在右边。
     * <p>
     * 因为快速排序已经很快了，增加插入排序也不会提升更多
     * if(r - l <= 8){
     *   InsertionSort.sort(arr, l, r);
     *   return;
     * }
     */
    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {

        // 避免有序数组导致的O(n)级别的递归深度，每次递归只减少一个数值而oom
        int random = new Random().nextInt(r - l + 1) + l;
        SortHelper.swap(arr, random, l);

        // j：小于 arr[l] 的位置
        int j = l;

        // i：大于 arr[l] 的位置
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(arr[l]) < 0) {
                j++;
                SortHelper.swap(arr, i, j);
            }
        }
        SortHelper.swap(arr, l, j);
        return j;
    }


}
