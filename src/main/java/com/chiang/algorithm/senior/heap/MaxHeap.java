package com.chiang.algorithm.senior.heap;


import com.chiang.algorithm.SortHelper;
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

    public void add(E e){
        data.addLast(e);

        shiftUp(getSize() -1);

    }

    public E get(int index){
        return data.get(index);
    }

    public E getMax(){
        if(isEmpty()){
            throw new RuntimeException("没有元素");
        }

        return data.get(0);
    }

    /**
     * 提取最大的元素
     */
    public E extractMax(){
        E e = getMax();
        data.swap(0,getSize() -1);
        data.removeLast();

        if(getSize() > 1) {
            shiftDown(0);
        }

        return e;
    }

    /**
     * 判断索引元素是否小于子元素，如果是就下沉
     *
     * @param index 索引元素
     */
    private void shiftDown(int index) {
        while (leftChild(index) < getSize()){
            int leftIndex = leftChild(index);
            if(leftIndex+1 < getSize()){
                int rightIndex = rightChild(index);
                if(get(rightIndex).compareTo(get(leftIndex)) > 0){
                    leftIndex++;
                }
            }

            if(get(leftIndex).compareTo(get(index)) > 0){
                data.swap(index,leftIndex);
                index = leftIndex;
            }else {
                break;
            }

        }

    }

    /**
     * 如果子元素大于父元素，就不断的递归上移子元素
     *
     * @param index 上移的元素索引
     */
    private void shiftUp(int index) {
        if(index <= 0){
            return;
        }

        int parentIndex = getParent(index);

        while (index > 0 && data.get(index).compareTo(data.get(parentIndex)) > 0){
            data.swap(index,parentIndex);
            index = parentIndex;
        }
    }

    public static void main(String[] args) {
        // 元素上浮
        System.out.println("元素上浮：");
        MaxHeap<Integer> maxHeap=new MaxHeap<>();
        maxHeap.add(2);
        maxHeap.add(3);
        System.out.println(99);
        for (int i = 0; i < maxHeap.getSize(); i++) {
            System.out.println(maxHeap.get(i));
        }

        System.out.println("提取最大元素：");
        Integer[] integers = SortHelper.generateArray(50);
        for (int i = 0; i < integers.length; i++) {
            maxHeap.add(integers[i]);
        }
        for (int i = 0; i < maxHeap.getSize(); i++) {
            System.out.println(maxHeap.extractMax());
        }
    }
}
