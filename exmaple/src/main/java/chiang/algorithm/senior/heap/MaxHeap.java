package chiang.algorithm.senior.heap;


import chiang.algorithm.foundation.arrary.DynamicArray;

/**
 * 最大堆，父节点永远是大于子节点的
 */
public class MaxHeap<E extends Comparable<E>> {
    private DynamicArray<E> data;

    public MaxHeap() {
        data = new DynamicArray<>();
    }

    /**
     * 使用heapify的方法，对已有数据的数组进行排序，比直接对空数组add效率要高，
     * 因为省去了最后的一层，直接省去了一半的计算，区别O(n)和O(nlogn)的差别。
     */
    public MaxHeap(E[] arr){
        this.data = new DynamicArray<>(arr);
        for (int i = getParent(arr.length-1); i>=0; i--) {
            shiftDown(i);
        }
    }

    public MaxHeap(int capacity) {
        data = new DynamicArray<>(capacity);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int size() {
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


    /**
     * 每次添加元素的时候，增加上浮循环，保证子节点是小于父节点的
     */
    public void add(E e){
        data.addLast(e);

        shiftUp(size() -1);

    }

    public E get(int index){
        return data.get(index);
    }

    public E findMax(){
        if(isEmpty()){
            throw new RuntimeException("没有元素");
        }

        return data.get(0);
    }

    /**
     * 提取最大的元素
     * 1. 将最大元素和最后一个元素交换，并函数最后一个元素
     * 2. 循环将最底部的元素下沉下去
     */
    public E extractMax(){
        E e = findMax();
        data.swap(0, size() -1);
        data.removeLast();

        if(size() > 1) {
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

        // 左边子元素还有就可以继续比较
        while (leftChild(index) < size()){
            int leftIndex = leftChild(index);

            // 右边的元素比左边的元素大，就索引+1
            if(leftIndex+1 < size()){
                int rightIndex = rightChild(index);
                if(get(rightIndex).compareTo(get(leftIndex)) > 0){
                    leftIndex++;
                }
            }

            // 将子元素最大的那一个，对index做比较，如果子元素大就做一个交换
            if(get(leftIndex).compareTo(get(index)) > 0){
                data.swap(index,leftIndex);
                index = leftIndex;
            }else {
                // 已经没有可以交换的元素，就跳出
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

        // 判断方法一定要写在循环里面，否则循环体的变量改变并不会影响到外面判断
        while (index > 0 &&  data.get(index).compareTo(data.get(getParent(index))) > 0){
            int parentIndex = getParent(index);
            data.swap(index,parentIndex);
            index = parentIndex;
        }
    }

    public static void main(String[] args) {
        // 元素上浮
        MaxHeap<Integer> maxHeap=new MaxHeap<>();
        Integer[] integers = new Integer[]{5,10,1,5,100};
        for (int i = 0; i < integers.length; i++) {
            maxHeap.add(integers[i]);
        }

        System.out.println("元素上浮：");
        for (int i = 0; i < maxHeap.size(); i++) {
            System.out.println(maxHeap.get(i));
        }

        System.out.println("提取最大元素：");
        for (int i = 0; i < integers.length; i++) {
            System.out.println(maxHeap.extractMax());
        }
    }
}
