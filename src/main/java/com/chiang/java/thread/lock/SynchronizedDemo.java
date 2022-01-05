package com.chiang.java.thread.lock;

import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. 对象锁：同一个类下不同的两个方法、synchronized(this)，会互相阻塞
 * 2. 类锁：静态方法、synchronized（类.class）会互相阻塞
 * 3. 类锁和对象锁，互不干涉
 * @author Chiang
 */
@SuppressWarnings("all")
public class SynchronizedDemo {

    public static void main(String[] args) {
//        objectSyn();
//        staticSyn();
        objectAndStaticSyn();
    }

    /**
     * 类所与对象锁互不干涉
     */
    private static void objectAndStaticSyn() {
        new Thread(()->{
            printA();
        }).start();

        SynchronizedDemo demo = new SynchronizedDemo();
        demo.addTask("A");
        demo.removeTask();
    }

    /**
     * 类锁，相互阻塞
     */
    private static void staticSyn() {
        System.out.println("-------------开启类锁-------------");
        new Thread(()->{
            printA();
        }).start();

        new Thread(()->{
            printB();
        }).start();
    }

    @SneakyThrows
    public synchronized static void printA() {
        System.out.println("printA 开始等待3秒："+LocalDateTime.now().getSecond());
        Thread.sleep(3000);
    }

    @SneakyThrows
    public static void printB(){
        synchronized (SynchronizedDemo.class){
            System.out.println("printB 等A完成了我再打印："+ LocalDateTime.now().getSecond());
        }
    }


    /**
     * 对象锁，相互阻塞
     */
    private static void objectSyn() {
        SynchronizedDemo demo = new SynchronizedDemo();
        System.out.println("-------------开启对象锁-------------："+LocalDateTime.now().getSecond());
        new Thread(()->{
            demo.addTask("A");
            System.out.println("A线程 添加成功");
        }).start();

        new Thread(()->{
            String task = demo.removeTask();
            System.out.println("B线程 成功删除了"+ task);
        }).start();

    }


    Queue<String> queue = new LinkedList<>();
    public synchronized void addTask(String s) {
        System.out.println("A线程 addTask 添加内容A");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.queue.add(s);
    }

    public synchronized String removeTask() {
        System.out.println("B线程 等待A线程完成添加后 removeTask");
        return queue.remove();
    }
}
