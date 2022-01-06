package com.chiang.java.thread.lock;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.*;

/**
 * 1.重复的lock和unlock不会报错
 * 2.读写锁互斥，读可以多个，写只能有一个
 *
 * @author Chiang
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
//        readAndWrite();

        lockConditionTest();
    }

    /**
     * lock.newCondition的lock与synchronize的wait是一样的
     * signal=notify
     * signalAll=notifyAll
     */
    private static void lockConditionTest() {
        LockCondition lockCondition = new LockCondition();
        new Thread(lockCondition::getTask).start();
        new Thread(()->{lockCondition.addTask("A");}).start();

    }

    /**
     * 读写锁互斥，不能同步操作
     */
    private static void readAndWrite() {
        ReentrantLockDemo readWriteLockMain = new ReentrantLockDemo();
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


    static class LockCondition {
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        private final Queue<String> queue = new LinkedList<>();

        public void addTask(String s) {
            lock.lock();
            try {
                queue.add(s);
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }

        @SneakyThrows
        public String getTask() {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    condition.await();
                }
                return queue.remove();
            } finally {
                lock.unlock();
            }
        }
    }
}