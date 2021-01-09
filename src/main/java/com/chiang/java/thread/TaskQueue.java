package com.chiang.java.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition提供的await()、signal()、signalAll()原理和synchronized锁对象的wait()、notify()、notifyAll()是一致的，并且其行为也是一样的。
 * @author Chiang
 */
public class TaskQueue {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Queue<String> queue = new LinkedList<>();

    public void addTask(String s) {
        lock.lock();
        try {
            queue.add(s);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String getTask() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
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
        TaskQueue taskQueue = new TaskQueue();

        new Thread(()->{
            try {
                Thread.sleep(5000);
                taskQueue.addTask("a");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        String task = taskQueue.getTask();

        System.out.println(task);
    }
}