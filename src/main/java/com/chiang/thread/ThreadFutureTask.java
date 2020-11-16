package com.chiang.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadFutureTask implements Callable<String> {
    public static void main(String[] args) throws Exception {
        ThreadFutureTask main = new ThreadFutureTask();
        FutureTask mainFutureTask = new FutureTask(main);
        new Thread(mainFutureTask).start();
        System.out.println(mainFutureTask.get());
    }

    public static void print(Object object){
        System.out.println(object);
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return "测试";
    }
}
