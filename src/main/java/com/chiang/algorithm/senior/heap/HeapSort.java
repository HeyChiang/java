package com.chiang.algorithm.senior.heap;

/**
 * 堆排序
 */
public class HeapSort {

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

}
