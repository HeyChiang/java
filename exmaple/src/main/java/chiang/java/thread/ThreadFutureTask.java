package chiang.java.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 可以返回值的异步线程
 * @author Chiang
 */
public class ThreadFutureTask implements Callable<String> {
    public static void main(String[] args) throws Exception {
        // FutureTask可以获取线程返回的值
        FutureTask<String> mainFutureTask = new FutureTask<>(new ThreadFutureTask());
        new Thread(mainFutureTask).start();

        //等待值的返回并打印
        System.out.println(mainFutureTask.get());
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return "测试";
    }
}
