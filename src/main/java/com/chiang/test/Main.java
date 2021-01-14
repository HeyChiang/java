package com.chiang.test;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 55; i++) {
            System.out.println(i%10);
        }
    }
}
