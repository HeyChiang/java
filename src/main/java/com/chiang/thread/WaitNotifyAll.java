package com.chiang.thread;

import java.time.Instant;

import static java.time.temporal.ChronoField.MILLI_OF_SECOND;

/**
 * 无论调用者是谁，一定要先锁（synchronized）才能成为这个对象的拥有者，
 * 成为拥有者以后就可以调用wait、notify、notifyAll了
 */
public class WaitNotifyAll {

    static final Object lock = new Object();

    /**
     * 生产者
     */
    void production(){
        new Thread(() ->{

            try {
                System.out.println("进入了 " + Thread.currentThread().getName());
                synchronized (lock){

                    System.out.println("同步了synchronized " + Thread.currentThread().getName());
                    Thread.sleep(3000);
                    lock.wait();
                    Thread.sleep(3000);
                }
                System.out.println("完成了synchronized " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }

    public static void main(String[] args) throws InterruptedException {

        WaitNotifyAll waitNotifyAll = new WaitNotifyAll();
        waitNotifyAll.production();
        waitNotifyAll.production();
        waitNotifyAll.production();

        Thread.sleep(10000);
        synchronized (lock){
            // 多个线程wait等待后，此处直接释放所有线程
            System.out.println("释放所有的lock，Thread-" + Thread.currentThread().getName());
            lock.notifyAll();
        }

    }

}