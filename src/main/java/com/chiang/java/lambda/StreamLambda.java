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
        List<String> filterList = strList.stream().filter((s -> s.length() > 7)).collect(Collectors.toList());
        System.out.println(filterList);

        // 对字符串进行操作后拿到结果
        List<String> mapList = strList.stream().map(s -> s + "--").collect(Collectors.toList());
        System.out.println(mapList);

        // 列表所有的数-100
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> newList = list.stream()
                .peek((x) -> System.out.println( x=x-100))
                .collect(Collectors.toList());
        // peek的操作不会影响集合的数据
        newList.forEach(System.out::println);

    }
}
