package com.chiang.java.thread.lock;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. Condition提供的await()、signal()、signalAll()原理和synchronized锁对象的wait()、notify()、notifyAll()其行为是一样的。
 * 2.
 * @author Chiang
 */
public class ReentrantLockCondition {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private final Queue<String> queue = new LinkedList<>();

    /**
     * 每当添加一个新的元素，唤醒condition的await
     */
    @SneakyThrows
    public void addTask(String s) {
        lock.lock();
        printInfo("addTask delay 3000");
        Thread.sleep(3000);
        try {
            queue.add(s);
            queue.add(s);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 1.队列是空的情况下，就condition等待，直到被唤醒
     * 2.每个线程都可以有自己的锁
     */
    public String getTask() {
        lock.lock();
        printInfo("getTask");
        try {
            while (queue.isEmpty()) {
                // 在await状态，去其他的线程是可以获取锁，进入等待的
                // 在addTask没有先锁的情况下，会有两个task进入获取了锁
                // 等addTask有2个参数就会全部打印出来
                condition.await();
            }
            return queue.remove();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return "non";
    }

    public static void main(String[] args) {
        ReentrantLockCondition lockCondition = new ReentrantLockCondition();

        new Thread(()->{
            lockCondition.addTask("content-delay-3000");
        }).start();

        new Thread(()->{
            printInfo(lockCondition.getTask());
        }).start();

        new Thread(()->{
            printInfo(lockCondition.getTask());
        }).start();

    }

    private static void printInfo(String task) {
        System.out.println(Thread.currentThread().getName()+"："+task);
    }
}