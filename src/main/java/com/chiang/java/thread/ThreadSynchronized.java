package com.chiang.java.thread;

import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 获取对象锁的两种方法，一实例对象指一个new Class
 1.同步代码块synchronized（this）和synchronized（类实例对象）
 2.同步非静态方法synchronized method，锁是当前对象的实例对象

 获取类锁的两种用法，整个类都同步了
 1.同步代码块synchronized（类.class），锁是小括号()的类对象
 2.同步静态方法synchronized static method ，锁是当前对象的类对象
 */
public class ThreadSynchronized {

    static class ThreadTestBean {
        public synchronized void test(String name){
            System.out.println(name + "进入"+Instant.now());
        }
    }

    public static void main(String[] args) {
        ThreadTestBean testBean= new ThreadTestBean();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> {
            while (true){
                testBean.test("AAA");
                Thread.sleep(5000);
            }
        });
        executorService.submit(()->{
           while (true){
               // 只有BBB线程在10秒执行完成后释放锁，AAA才有执行的机会。
               // 见证静态方法锁和synchronized (testBean)是一个东西
               synchronized (testBean){
                   testBean.test("BBB");
                   Thread.sleep(10000);
               }
           }
        });


    }
}
