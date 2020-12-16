package com.chiang.java.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamLambda {
    public static void main(String[] args) {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("zhangsan");
        strList.add("lisi");
        strList.add("wangwu");
        strList.add("zhaoliu");
        strList.add("qianqi");

        // 筛选名称长度大于7的
        List<String> collect = strList.stream().filter((s -> s.length() > 7)).collect(Collectors.toList());
        System.out.println(collect);



        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> newList1 = list.stream()
                .peek(System.out::println)
                .collect(Collectors.toList());

        List<Integer> newList2 = list.stream()
                .peek((x) -> System.out.println( x=x-100))
                .collect(Collectors.toList());

        System.out.println(Integer.MIN_VALUE);

    }
}
