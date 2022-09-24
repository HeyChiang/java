package chiang.java.thread.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * StampLock比起ReentrantLock多了乐观锁，在读写使用乐观锁去读取参数，在写的场景少的情况下，
 * 使用StampLock更好，JDK 1.8版本开始启用
 * @author jianghao
 */
public class StampedLockDemo {
    private final StampedLock stampedLock = new StampedLock();

    private double x;
    private double y;

    /**
     * 获取写锁，写入参数
     */
    public void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    /**
     * 使用乐观锁去获取参数，然后对比乐观锁版本，版本有变化则开启悲观锁
     */
    public double distanceFromOrigin() {
        // 获得一个乐观读锁
        long stamp = stampedLock.tryOptimisticRead();
        // 注意下面两行代码不是原子操作
        // 假设x,y = (100,200)
        double currentX = x;
        // 此处已读取到x=100，但x,y可能被写线程修改为(300,400)
        double currentY = y;
        // 此处已读取到y，如果没有写入，读取是正确的(100,200)
        // 如果有写入，读取是错误的(100,400)
        // 检查乐观读锁后是否有其他写锁发生
        if (!stampedLock.validate(stamp)) {
            // 获取一个悲观读锁
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                // 释放悲观读锁
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}