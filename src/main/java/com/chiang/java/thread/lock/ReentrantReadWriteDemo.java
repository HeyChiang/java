package com.chiang.java.thread.lock;

import java.util.concurrent.locks.*;

/**
 * 1.重复的lock和unlock不会报错
 * 2.读写锁互斥，读可以多个，写只能有一个
 *
 * @author Chiang
 */
public class ReentrantReadWriteDemo {

    /**
     * 读写锁互斥，不能同步操作
     */
    public static void main(String[] args) {
        ReentrantReadWriteDemo readWriteLockMain = new ReentrantReadWriteDemo();
        new Thread(readWriteLockMain::writeMethod).start();
        new Thread(readWriteLockMain::readMethod).start();
        new Thread(readWriteLockMain::readMethod).start();
    }

    private final ReadWriteLock rwlock = new ReentrantReadWriteLock();
    private void readMethod() {
        rwlock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + " 获得了读锁");
        try {
            System.out.println(Thread.currentThread().getName() + " 正在执行读操作");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            rwlock.readLock().unlock();
            System.out.println(Thread.currentThread().getName() + " 释放了读锁");
        }
    }

    private void writeMethod() {
        rwlock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + " 获得了写锁");
        try {
            System.out.println(Thread.currentThread().getName() + " 正在执行写操作");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            rwlock.writeLock().unlock();
            System.out.println(Thread.currentThread().getName() + " 释放了写锁");
        }
    }
}