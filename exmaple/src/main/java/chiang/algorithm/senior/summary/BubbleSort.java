package chiang.algorithm.senior.summary;

import chiang.algorithm.SortHelper;

/**
 * 冒泡排序
 *
 * @author Chiang
 */
public class BubbleSort {
    public static void main(String[] args) {
        Integer[] integers = SortHelper.generateArray(10);
        SortHelper.print("原始： ", integers);

        Sort1(integers);
        Sort2(integers);

        SortHelper.print(integers);
    }

    /**
     * 冒泡排序的优化版，如果后续数组有序的，就减少i循环的次数
     */
    private static void Sort2(Integer[] integers) {
        // 确定循环次数，因为是相邻两个数，最后一个数不需要对比，所以i+1
        for (int i = 0; i + 1 < integers.length; i++) {

            for (int j = 0; j < integers.length - i - 1; j++) {
                int lastIndex = 0;
                if (integers[j] > integers[j + 1]) {
                    SortHelper.swap(integers, j, j + 1);
                    lastIndex = j + 1;
                }

                // 如果最后8个都是有序的，这里就-8，i循环只要再执行1次就可以了
                // j 还是从0开始，改变执行次数不影响对前面的数组判断和交换
                i = integers.length - lastIndex;
            }
        }
    }

    /**
     * 普通冒泡排序
     */
    private static void Sort1(Integer[] integers) {
        // 确定循环次数，因为是相邻两个数，最后一个数不需要对比，所以i+1
        for (int i = 0; i + 1 < integers.length; i++) {

            // 每次冒泡以后，最大的数在最后，所以要-i
            // 由于相邻的数字对比，最后一个数字没有相邻数，所以固定-1
            for (int j = 0; j < integers.length - i - 1; j++) {
                if (integers[j] > integers[j + 1]) {
                    SortHelper.swap(integers, j, j + 1);
                }
            }
        }
    }
}
