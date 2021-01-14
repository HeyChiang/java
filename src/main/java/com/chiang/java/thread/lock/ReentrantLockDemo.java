package com.chiang.java.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重复的lock和unlock不会报错
 * @author Chiang
 */
public class ReentrantLockDemo {
    final Lock lock = new ReentrantLock();
    int count;

    public void add(int n) {
        lock.lock();
        try {
            count += n;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo counter = new ReentrantLockDemo();
        counter.lock.lock();
        try{
            counter.add(1);
            System.out.println(counter.count);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            counter.lock.unlock();
        }
    }
}