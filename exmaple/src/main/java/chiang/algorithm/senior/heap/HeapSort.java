package chiang.algorithm.senior.heap;

import chiang.algorithm.SortHelper;

/**
 * 堆排序
 */
public class HeapSort {

    public static void main(String[] args) {
        Integer[] integers = SortHelper.generateArray(10);
        sort(integers);
        for (int i = 0; i < integers.length; i++) {
            System.out.print(integers[i] + " ");
        }

        System.out.println();

        integers = SortHelper.generateArray(10);
        sort2(integers);
        for (int i = 0; i < integers.length; i++) {
            System.out.print(integers[i] + " ");
        }
    }

    /**
     * 通过堆对数组从小到大的排序
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        MaxHeap<E> maxHeap = new MaxHeap<>();
        for (E e : arr) {
            maxHeap.add(e);
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = maxHeap.extractMax();
        }
    }

    /**
     * 原地对堆进行排序操作
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        if (arr.length < 2) {
            // 数组只有一个元素，或者一个元素都没有
            return;
        }

        // 对数组原地排序
        for (int i = arr.length - 1; i >= 0; i--) {
            shiftDown(arr, i, arr.length);
        }

        // 从小到达的排序
        for (int i = arr.length - 1; i >= 0; i--) {
            // 把最大的元素放到最后去，之后不再参与排序
            SortHelper.swap(arr, 0, i);
            // 对i以内的元素进行shitDown，把最大的元素上浮，小元素下沉
            shiftDown(arr, 0, i);
        }
    }

    /**
     * 将index元素下沉
     *
     * @param arr 数组数据
     * @param index 下沉的索引
     * @param n 数组长度
     */
    private static <E extends Comparable<E>> void shiftDown(E[] arr, int index, int n) {

        // 左边子元素还有就可以继续比较
        while (index * 2 + 1 < n) {

            // 左边索引的位置
            int swapIndex = index * 2 + 1;

            // 右边的元素比左边的元素大，就索引+1
            if (swapIndex + 1 < n) {
                int rightIndex = swapIndex + 1;
                if (arr[rightIndex].compareTo(arr[swapIndex]) > 0) {
                    swapIndex++;
                }
            }

            // 将子元素最大的那一个，对index做比较，如果子元素大就做一个交换
            if (arr[swapIndex].compareTo(arr[index]) > 0) {
                SortHelper.swap(arr, index, swapIndex);
                index = swapIndex;
            } else {
                // 已经没有可以交换的元素，就跳出
                break;
            }

        }

    }

}
