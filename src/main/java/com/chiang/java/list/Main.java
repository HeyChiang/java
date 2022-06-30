package com.chiang.java.list;

import com.chiang.algorithm.SortHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * removeAll的删除会删除所有集合内相同的参数
 *
 * @author JiangHao
 */
public class Main {

    public static void main(String[] args) {
        Integer[] integers = SortHelper.generateArray(98);
        List<Integer> list = new ArrayList<>(Arrays.asList(integers));

        int rmSum = 0 ;
        while (!list.isEmpty()) {

            int size = list.size() % 10;
            int removeSize = size == 0 ? 10 : size;

            List<Integer> removeList = list.subList(0, removeSize);
            rmSum += removeSize;

            System.out.println("删除：" + removeList.size());
            list.removeAll(removeList);
        }
        System.out.println("总共删除："+rmSum);
    }
}
