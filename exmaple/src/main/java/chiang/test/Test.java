package chiang.test;
 
import java.util.concurrent.Exchanger;
 
class ExchangerTest extends Thread {
    private final Exchanger<String> exchanger;
    private final String string;
 
    public ExchangerTest(Exchanger<String> exchanger, String string) {
        this.exchanger = exchanger;
        this.string = string;
    }
 
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + ": " + exchanger.exchange(string));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
 
public class Test {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ExchangerTest test1 = new ExchangerTest(exchanger, "string1");
        ExchangerTest test2 = new ExchangerTest(exchanger, "a");
 
        test1.start();
        test2.start();
    }
}