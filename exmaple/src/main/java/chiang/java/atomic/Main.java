package chiang.java.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger(0);

        //如果期望值与
        boolean b = atomicInteger.compareAndSet(0, 2);
        System.out.println(b+" -- "+atomicInteger.get());
    }
}
