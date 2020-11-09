package com.chiang.thread;

import java.time.Instant;

import static java.time.temporal.ChronoField.MILLI_OF_SECOND;

public class Test {
    public static void main(String[] args) {

        Thread.yield();

        Instant instant= Instant.now();
        System.out.println(instant.get(MILLI_OF_SECOND));

    }
}
