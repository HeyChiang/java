package com.chiang.algorithm.senior.heap;


import com.chiang.algorithm.SortHelper;

/**
 * 输入整数数组arr ，找出其中最小的k个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 */
class FindRangeKSolution {

    public int[] getLeastNumber(Integer[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        // 使用最大堆，先保存k个数
        for (int i = 0; i < k; i++) {
            queue.enqueue(i);
        }

        // 如果数字大于最大堆的第一个数字，就加入队列
        for (int i = k; i < arr.length; i++) {
            if (!queue.isEmpty() && arr[i] < queue.getFront()) {
                queue.dequeue();
                queue.enqueue(arr[i]);
            }
        }

        // 将所有的结果都添加到数组中
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.dequeue();
        }

        return res;
    }

    public static void main(String[] args) {
        Integer[] integers = SortHelper.generateArray(5);
        int[] leastNumber = new FindRangeKSolution().getLeastNumber(integers, 3);
        for (int j : leastNumber) {
            System.out.print(j + " ");
        }
    }
}