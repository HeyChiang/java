package com.chiang.algorithm;

import java.util.Objects;
import java.util.Random;

public class SortHelper {



    /**
     * 生成数组
     */
    public static Integer[] generateArray(Integer num) {
        Random random = new Random();
        Integer[] array = new Integer[num];
        for (int i = 0; i < num; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }


    /**
     * 交换 i 与 j 的位置
     */
    private static <E> void swap(E[] arr, int i, int j) {
        E e = arr[j];
        arr[j] = arr[i];
        arr[i] = e;
    }

    public static <E> void print(String str,E[] array){

        if(Objects.nonNull(str)){
            System.out.print(str);
        }

        StringBuilder buffer=new StringBuilder();
        for (E e : array) {
            buffer.append(e);
            buffer.append(",");
        }
        int last = buffer.lastIndexOf(",");
        if(last == buffer.length()-1){
            buffer.delete(last,buffer.length());
        }
        System.out.println(buffer);
        System.out.println("");
    }

    public static <E> void print(String str,E[] array,int l ,int r){
        print(str, array, l, r,0);
    }

    /**
     *
     * @param offset 补偿，有些运算打印要比数组多1
     */
    public static <E> void print(String str,E[] array,int l ,int r,int offset){

        if(Objects.nonNull(str)){
            System.out.print(str +" l:"+l+" r:"+(r+offset) +" 内容：");
        }

        for (int i = l; i < r+offset; i++) {
            System.out.print(array[i]);
            System.out.print(",");
        }

        System.out.println("");
    }
}
