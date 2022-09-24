package chiang.algorithm.senior.heap;

// Leetcode 215
// https://leetcode-cn.com/problems/kth-largest-element-in-an-array/

import chiang.algorithm.SortHelper;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 */
public class FindKLargeSolution {

    /**
     * 找出第k大的元素。
     *  1. 整个数组的长度就是k
     *  2. 只要大于堆里的最小元素，就塞堆里，所有大的元素都在堆里
     *  3. 最小堆里，第一个就是k大的元素
     *
     * @param nums 数组数据
     * @param k 要找的第K大的元素
     * @return 被找到的k大元素
     */
    public int findKthLargest(Integer[] nums, int k) {

        // 创建最小堆，并将k之前所有的数组放入
        MinHeap<Integer> pq = new MinHeap<>();
        for(int i = 0; i < k; i ++)
            pq.add(nums[i]);

        // 如果数组大于堆的最小元素，就删除替换掉
        for(int i = k; i < nums.length; i ++)
            if(!pq.isEmpty() && nums[i] > pq.findMin()){
                pq.replace(nums[i]);
            }

        // 返回堆的最小元素
        return pq.findMin();
    }


    public static void main(String[] args) {
        Integer[] integers = SortHelper.generateArray(5);
        for (Integer i : integers) {
            System.out.print(i + " ");
        }
        System.out.println();

        int findKthLargest = new FindKLargeSolution().findKthLargest(integers, 3);
        System.out.println(findKthLargest);
    }
}
