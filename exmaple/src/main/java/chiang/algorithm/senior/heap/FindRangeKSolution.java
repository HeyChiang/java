package chiang.algorithm.senior.heap;


import chiang.algorithm.SortHelper;

import java.util.Comparator;

/**
 * 输入整数数组arr ，找出其中最小的k个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 */
class FindRangeKSolution {

    public static void main(String[] args) {
        Integer[] integers = SortHelper.generateArray(5);

        SortHelper.print("数据：",integers);

        int[] leastNumber = new FindRangeKSolution().getLeastNumber(integers, 3);
        for (int j : leastNumber) {
            System.out.print(j + " ");
        }

        System.out.println();
        int[] leastNumber2 = new FindRangeKSolution().getLeastNumber2(integers, 3);
        for (int j : leastNumber2) {
            System.out.print(j + " ");
        }
    }

    /**
     * 通过最大堆找出最小的k范围数据
     */
    public int[] getLeastNumber2(Integer[] arr,int k){
        // 默认是最小堆，必须使用最大堆来存放最小的几个数
        java.util.PriorityQueue<Integer> queue = new java.util.PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < k; i++) {
            queue.add(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if(!queue.isEmpty() && arr[i] < queue.peek()){
                // 使用最大堆，在删除的时候才会删除最大的数字
                queue.remove();
                queue.add(arr[i]);
            }
        }

        int[] least = new int[k];
        for (int i = 0; i < k; i++) {
            least[i] = queue.remove();
        }

        return least;
    }

    /**
     *  获取最小的k个元素.
     *
     *  1. 塞入k个数字到堆
     *  2. 循环比较，小于堆第一个元素的就塞入堆，得到最小的k个元素
     *  3. 返回数组
     */
    public int[] getLeastNumber(Integer[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        // 使用最大堆，先保存k个数
        for (int i = 0; i < k; i++) {
            queue.enqueue(arr[i]);
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
}