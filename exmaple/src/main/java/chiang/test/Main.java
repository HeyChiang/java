package chiang.test;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        long s1 = System.currentTimeMillis();
        String str = "select * FROM dj_store_xx left join haha where id=1";
        boolean matches = str.matches(".+(?i)from\\s+(?i)dj_store_.+");
        long s2 = System.currentTimeMillis();
        System.out.println(s2-s1);
        System.out.println(matches);
    }
}
