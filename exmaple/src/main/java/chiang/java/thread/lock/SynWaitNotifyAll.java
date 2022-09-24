package chiang.java.thread.lock;

/**
 * 1.无论调用者是谁，一定要先锁（synchronized）才能成为这个对象的拥有者，成为拥有者以后就可以调用wait、notify、notifyAll了
 * 2.synchronized 获取锁和唤醒都是随机的
 */
public class SynWaitNotifyAll {

    static final Object lock = new Object();

    /**
     * 生产者
     */
    void production(){
        new Thread(() ->{

            try {
                print("准备入锁 ");
                synchronized (lock){

                    print("进入了 synchronized");
                    Thread.sleep(3000);
                    print("Wait 让出了锁 ");
                    lock.wait();
                    Thread.sleep(3000);
                }
                print("出了 synchronized ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }

    private void print(String s) {
        System.out.println(Thread.currentThread().getName() +":"+s);
    }

    public static void main(String[] args) throws InterruptedException {

        SynWaitNotifyAll synWaitNotifyAll = new SynWaitNotifyAll();
        synWaitNotifyAll.production();
        synWaitNotifyAll.production();
        synWaitNotifyAll.production();

        Thread.sleep(10000);
        synchronized (lock){
            // 多个线程wait等待后，此处直接释放所有线程
            System.out.println("释放所有的lock，Thread-" + Thread.currentThread().getName());
            lock.notifyAll();
        }

    }

}