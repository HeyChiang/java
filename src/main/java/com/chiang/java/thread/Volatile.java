package com.chiang.java.thread;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

/**
 * volatile 变量直接操作内存，不再使用CPU的缓存,
 * 可以保证读取到的变量值是最新的，但是不保证操作的原子性,所以本Demo在并发搞的时候，无法打印出1000 00
 * @author JiangHao
 */
@SuppressWarnings("all")
public class Volatile {
    private CountDownLatch latch = new CountDownLatch(100);
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    @SneakyThrows
    public static void main(String[] args) {
        final Volatile test = new Volatile();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    test.increase();
                }
                test.latch.countDown();
            }).start();
        }

        //保证前面的线程都执行完
        test.latch.await();
        System.out.println(test.inc);
    }
}
