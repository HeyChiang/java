package com.chiang.algorithm.senior.segment;

/**
 * 线段树某一段数值的总和测试
 *
 * 该测试用例来源：Leetcode 303. Range Sum Query - Immutable
 * https://leetcode.com/problems/range-sum-query-immutable/description/
 *
 */
public class Main {

    public static void main(String[] args) {

        Integer[] nums = {-2, 0, 3, -5, 2, -1};

        SegmentTree<Integer> segTree = new SegmentTree<>(nums,Integer::sum);
        System.out.println(segTree);

        System.out.println(segTree.query(0, 2));
        segTree.set(1,10);
        System.out.println(segTree.query(0, 2));
    }
}
