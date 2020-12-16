package com.chiang.java.lambda;

import java.util.*;

public class StreamPerformance {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            list.add(random.nextInt(Integer.MAX_VALUE));
        }

        // 并行测试
        testParallelStream(list);
        // for循环测试
        testStreamFor1(list);
        // for增强测试
        testStreamFor2(list);
        // 迭代器测试
        testStreamIterator(list);
        // streamMax测试
        testStreamMax(list);
    }

    public static void testStreamMax(List<Integer> list){
        long startTime = System.currentTimeMillis();

        Optional<Integer> optional = list.stream().max(Integer::compareTo);
        System.out.println();

        long endTime = System.currentTimeMillis();
        System.out.println("testSteamMax执行时间:" + (endTime - startTime) +"ms");
    }

    public static void testParallelStream(List<Integer> list){
        long startTime = System.currentTimeMillis();

        Optional<Integer> optional = list.parallelStream().max(Integer::compareTo);
        System.out.println();

        long endTime = System.currentTimeMillis();
        System.out.println("testParallelSteam执行时间:" + (endTime - startTime) +"ms");
    }

    public static void testStreamFor1(List<Integer> list){
        long startTime = System.currentTimeMillis();

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) {
            int current = list.get(i);
            if (current > max){
                max = current;
            }
        }
        System.out.println();

        long endTime = System.currentTimeMillis();
        System.out.println("testSteamFor1执行时间:" + (endTime - startTime) +"ms");
    }

    public static void testStreamFor2(List<Integer> list){
        long startTime = System.currentTimeMillis();

        int max = Integer.MIN_VALUE;
        for (Integer i :list){
            if( i > max){
                max = i;
            }
        }
        System.out.println();

        long endTime = System.currentTimeMillis();
        System.out.println("testSteamFor2执行时间:" + (endTime - startTime) +"ms");
    }
    public static void testStreamIterator(List<Integer> list){
        long startTime = System.currentTimeMillis();

        Iterator<Integer> iterator = list.iterator();
        int max = iterator.next();
        while (iterator.hasNext()){
            Integer integer = iterator.next();
            if(integer > max){
                max = integer;
            }
        }
        System.out.println();

        long endTime = System.currentTimeMillis();
        System.out.println("testSteamIterator执行时间:" + (endTime - startTime) +"ms");
    }
}
