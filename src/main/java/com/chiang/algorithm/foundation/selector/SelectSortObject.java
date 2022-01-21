package com.chiang.algorithm.foundation.selector;

import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * 实现compareTo的方法进行对象比较，两个循环的算法复杂度是O(n^2)
 *
 * @author Chiang
 */
public class SelectSortObject {

    public static void main(String[] args) {
        Student[] students =new Student[]{
                new Student("bobo",10),
                new Student("zhangsan",9),
                new Student("lowu",22)
        };

        // array[0..i)是已排序的，array[i..n)是未排序的
        for (int i = 0; i < students.length; i++) {
            for (int j = i + 1; j < students.length; j++) {

                // 当前i对象分数小于j的，就调换
                if (students[i].compareTo(students[j]) < 0) {
                    swap(i, j, students);
                }
            }
        }

        Arrays.asList(students).forEach(System.out::println);
    }

    /**
     * 交换数组内参数的位置
     */
    private static <E> void swap(int i, int j, E[] array) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @AllArgsConstructor
    static
    class Student implements Comparable<Student> {

        private String name;
        private int score;

        @Override
        public int compareTo(Student o) {
            return o.score - this.score;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}
