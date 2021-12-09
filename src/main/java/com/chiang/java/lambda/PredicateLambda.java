package com.chiang.java.lambda;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Java 8特性，断言
 * @author JiangHao at 12/9/2021 7:47 PM
 */
public class PredicateLambda {

    public static void main(String[] args) {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("55555");
        strList.add("1");
        strList.add("22");
        strList.add("333");
        strList.add("4444");

        Predicate<String> limit = s -> s.length() >4;

        strList.stream().filter(limit).forEach(System.out::println);
    }
}
