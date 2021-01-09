package com.chiang.java.thread;

import java.util.concurrent.*;

/**
 * 资
 * @author Chiang
 */
public class FutureCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<String> submit = executorService.submit(() -> {
            try {
                System.out.println("等待3秒");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "张三";
        } );

        // 等待执行完成的线程数据返回
        System.out.println(submit.get());
    }
}
