package chiang.java.list;

import chiang.algorithm.SortHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * removeAll会删除所有集合内相同的参数，并不是一一对应的删除
 *
 * @author JiangHao
 */
public class Main {

    public static void main(String[] args) {
        Integer[] integers = SortHelper.generateArray(98);
        List<Integer> list = new ArrayList<>(Arrays.asList(integers));

        int rmSum = 0 ;
        while (!list.isEmpty()) {

            // 每次删除文件的大小
            int size = list.size() % 10;
            int removeSize = size == 0 ? 10 : size;

            List<Integer> removeList = list.subList(0, removeSize);
            rmSum += removeSize;

            System.out.println("删除：" + removeList.size());
            // 如果有相同数据，会删除多个
            list.removeAll(removeList);
        }
        System.out.println("总共删除："+rmSum);
    }
}
