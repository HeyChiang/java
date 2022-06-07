package com.chiang.algorithm.senior.heap;


import com.chiang.algorithm.foundation.arrary.DynamicArray;

/**
 * 最大堆，根节点永远是最大的
 */
public class MaxHeap<E extends Comparable<E>> {
    private DynamicArray<E> data;

    public MaxHeap() {
        data = new DynamicArray<>();
    }

    public MaxHeap(int capacity) {
        data = new DynamicArray<>(capacity);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int getSize() {
        return data.getSize();
    }

    /**
     * 获取父节点
     * @param index 当前节点索引
     */
    public int getParent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("0 not have parent");
        }
        return (index - 1) / 2;
    }

    /**
     * 获取节点左边的索引
     * @param index 父节点索引
     */
    public int leftChild(int index) {
        return (index * 2) + 1;
    }

    /**
     * 获取右节点
     * @param index 父节点索引
     */
    public int rightChild(int index) {
        return (index * 2) + 2;
    }
}
