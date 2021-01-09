package com.chiang.java.thread;

public class Volatile {
    static class Work {
        boolean isShutDown = false;

        void shutdown() {
            isShutDown = true;
            System.out.println("shutdown! " + Thread.currentThread().getName());
        }

        void doWork() {
            while (!isShutDown) {
                System.out.println("doWork " + Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        Work work = new Work();

        new Thread(work::doWork).start();
        new Thread(work::doWork).start();
        new Thread(work::doWork).start();
        new Thread(work::shutdown).start();
        new Thread(work::doWork).start();
        new Thread(work::doWork).start();
        new Thread(work::doWork).start();
    }
}
