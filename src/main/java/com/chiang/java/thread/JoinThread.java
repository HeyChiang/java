package com.chiang.java.thread;

public class JoinThread {
    static String str = "a";

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(5000);
                str = "b";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();

        // 等待t线程死亡才会进入下一步
        t.join();

        System.out.println(str);
    }
}
