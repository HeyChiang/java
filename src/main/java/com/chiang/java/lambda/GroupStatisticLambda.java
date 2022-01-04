package com.chiang.java.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 使用lambda对数据集合进行分组、统计
 * @author JiangHao at 1/4/2022 9:00 AM
 */
public class GroupStatisticLambda {
    public static void main(String[] args) {
        User user1 = new User("zhangsan", 60, 20);
        User user2 = new User("lisi", 80, 23);
        User user3 = new User("zhangsan", 80, 24);
        User user4 = new User("wangwu", 50, 24);
        User user5 = new User("wangwu2", 50, 24);

        List<User> userList = Arrays.asList(user1, user2, user3, user4, user5);

        //算出分数最小的那个并输出
        userList.stream().min(Comparator.comparingInt(User::getScore)).ifPresent(System.out::println);

        //算出分数最大的那个并输出（无法做到多个并列的时候求值）
        Optional<User> optional = userList.stream().max(Comparator.comparingInt(User::getScore));
        //isPresent是判断是否存在，不能接受参数
        optional.ifPresent(System.out::println);

        //算出分数平均值并输出
        double averageInt = userList.stream().collect(Collectors.averagingInt(User::getScore));
        System.out.println("算出分数平均值并输出 = " + averageInt);

        //算出分数总和并输出
        int summingInt = userList.stream().mapToInt(User::getScore).sum();
        System.out.println("算出分数总和并输出 = " + summingInt);

        //算出汇总信息
        IntSummaryStatistics intSummaryStatistics = userList.stream().collect(Collectors.summarizingInt(User::getScore));
        System.out.println("算出汇总信息 = " + intSummaryStatistics);

        //拼接名字
        String nameStrs = userList.stream().map(User::getName).collect(Collectors.joining(", "));
        System.out.println("拼接名字 = " + nameStrs);

        //拼接名字，调用另外一个方法，可以加前缀和后缀
        String nameStrs2 = userList.stream().map(User::getName).collect(Collectors.joining("： ", "[", "]"));
        System.out.println("拼接名字，调用另外一个方法，可以加前缀和后缀 = " + nameStrs2);

        //分组：按照分数（返回的map的key是根据分组的条件来决定的，score是int，那么key就是Integer）
        Map<Integer, List<User>> scoreUsers = userList.stream().collect(Collectors.groupingBy(User::getScore));
        System.out.println("分组：按照分数 = " + scoreUsers);

        //二级分组：线按照分数分组，返回一个Map<Integer, List<User>>, 在根据用户名分组
        Map<Integer, Map<String, List<User>>> scoreNameUsers = userList.stream().collect(Collectors.groupingBy(User::getScore, Collectors.groupingBy(User::getName)));
        System.out.println("二级分组：线按照分数分组 = " + scoreNameUsers);

        //分区,是否及格
        Map<Boolean, List<User>> jigeUsers = userList.stream().collect(Collectors.partitioningBy(user -> user.getScore() >= 60));
        System.out.println("分区,是否及格 = " + jigeUsers);

        //二级分区,是否及格,及格里面是否大于80
        Map<Boolean, Map<Boolean, List<User>>> youxiuUsers = userList.stream().collect(Collectors.partitioningBy(user -> user.getScore() >= 60, Collectors.partitioningBy(user -> user.getScore() >= 80)));
        System.out.println("二级分区,是否及格 = " + youxiuUsers);

        //分区,是否及格,算出及格的个数
        Map<Boolean, Long> jigeUserCount = userList.stream().collect(Collectors.partitioningBy(user -> user.getScore() >= 60, Collectors.counting()));
        System.out.println("分区,是否及格 = " + jigeUserCount);

        //先按照名字分组,获取每个分组分数最小的
        Map<String, User> userCount = userList.stream().collect(Collectors.toMap(User::getName, Function.identity(), BinaryOperator.minBy(Comparator.comparingInt(User::getScore))));
        System.out.println("先按照名字分组 = " + userCount);


    }

    @Data
    @AllArgsConstructor
    static class User{
        String name;
        int score;
        int age;
    }
}

