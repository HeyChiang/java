package chiang.java.thread.lock;

import lombok.SneakyThrows;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition提供的await()、signal()、signalAll()原理和synchronized锁对象的wait()、notify()、notifyAll()其行为是一样的。
 * 1. 开启三个线程，一个线程先进行队列里添加2个元素，但是延期3秒
 * 2. 两个线程从队列里获取元素，如果队列是空的就等待
 * 3. 元素添加完成以后，其他的线程也释放出来了
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
     * 2.锁只有一个，只有当另外一个现场await释放，其他的所才能被获取到
     */
    public String getTask() {
        // 一定要先获取锁，才有资格调用await操作,否则会报错:IllegalMonitorStateException
        lock.lock();
        printInfo("getTask");
        try {
            Thread.sleep(1000);
            printInfo("getTask");
            while (queue.isEmpty()) {
                // 在await进入等待的情况，去其他的线程是可以获取锁，
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
            lockCondition.addTask("content-delay-3000 " + new Date());
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