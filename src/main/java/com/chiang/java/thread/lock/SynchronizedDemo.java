package com.chiang.java.thread.lock;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 同一个类两个方法使用synchronized会互相阻塞
 * @author Chiang
 */
public class SynchronizedDemo {
    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String s) {
        this.queue.add(s);
    }

    public synchronized String getTask() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return queue.remove();
    }

    @SuppressWarnings("Ali-Check")
    public static void main(String[] args) {
        SynchronizedDemo demo = new SynchronizedDemo();
        demo.addTask("A");

        new Thread(()->{
            System.out.println("A线程 getTask 阻塞5秒");
            String task = demo.getTask();
            System.out.println("A线程 获取到"+ task);
        }).start();

        new Thread(()->{
            System.out.println("B线程 addTask 等待A线程执行完");
            demo.addTask("B");
            System.out.println("B线程 添加成功");
        }).start();
    }
}
