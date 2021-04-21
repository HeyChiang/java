package com.chiang.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreaker.State;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreakerStrategy;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.EventObserverRegistry;
import com.alibaba.csp.sentinel.util.TimeUtil;

/**
 * 熔断Demo示例，定义一个资源后，选择异常的比例、慢调用的数量、异常数量三个模式
 * 的其中一种，当超出规则时发生熔断
 *
 * @author jianghao
 */
public class DegradeRuleDemo {

    /**
     * 定义资源
     */
    private static final String KEY = "some_method";

    /**
     * 定义tick时钟，每秒答应一次统计结果
     */
    private static volatile boolean stop = false;
    private static int seconds = 120;

    /**
     * 定义计数器，记录资源访问的总次数、通过次数、被锁的次数
     */
    private static final AtomicInteger TOTAL = new AtomicInteger();
    private static final AtomicInteger PASS = new AtomicInteger();
    private static final AtomicInteger BLOCK = new AtomicInteger();

    @SuppressWarnings("all")
    public static void main(String[] args) throws Exception {
        initDegradeRule();
        registerStateChangeObserver();
        startTick();

        int concurrency = 8;
        for (int i = 0; i < concurrency; i++) {
            Thread entryThread = new Thread(() -> {
                while (true) {
                    Entry entry = null;
                    try {
                        entry = SphU.entry(KEY);
                        PASS.incrementAndGet();
                        // 定义睡眠事件在40~60毫秒之间
                        sleep(ThreadLocalRandom.current().nextInt(40, 60));
                    } catch (BlockException e) {
                        BLOCK.incrementAndGet();
                        sleep(ThreadLocalRandom.current().nextInt(5, 10));
                    } finally {
                        TOTAL.incrementAndGet();
                        if (entry != null) {
                            entry.exit();
                        }
                    }
                }
            });
            entryThread.setName("sentinel-simulate-traffic-task-" + i);
            entryThread.start();
        }
    }

    /**
     * Sentinel 支持注册自定义的事件监听器监听熔断器状态变换事件
     */
    private static void registerStateChangeObserver() {
        EventObserverRegistry.getInstance().addStateChangeObserver("logging",
            (prevState, newState, rule, snapshotValue) -> {
                if (newState == State.OPEN) {
                    System.err.println(String.format("%s -> OPEN at %d, snapshotValue=%.2f", prevState.name(),
                        TimeUtil.currentTimeMillis(), snapshotValue));
                } else {
                    System.err.println(String.format("%s -> %s at %d", prevState.name(), newState.name(),
                        TimeUtil.currentTimeMillis()));
                }
            });
    }

    /**
     * 初始化熔断的规则
     */
    private static void initDegradeRule() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule(KEY)
            // 熔断策略，支持慢调用比例/异常比例/异常数策略
            .setGrade(CircuitBreakerStrategy.SLOW_REQUEST_RATIO.getType())
            // 慢调用比例模式下为慢调用临界 RT（超出该值毫秒返回事件计为慢调用）；异常比例/异常数模式下为对应的阈值
            .setCount(50)
            // 熔断10秒，之后会重新尝试
            .setTimeWindow(10)
            // 慢调用比例阈值，仅慢调用比例模式有效 > 60%
            .setSlowRatioThreshold(0.6)
            // 熔断触发的最小请求数，请求数小于该值时即使异常比率超出阈值也不会熔断
            .setMinRequestAmount(100)
            // 统计时长（单位为 ms），如 60*1000 代表分钟级
            .setStatIntervalMs(2 * 1000);
        rules.add(rule);

        DegradeRuleManager.loadRules(rules);
        System.out.println("Degrade rule loaded: " + rules);
    }

    /**
     * 线程的睡眠事件
     * @param timeMs 毫秒数
     */
    private static void sleep(int timeMs) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeMs);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    private static void startTick() {
        Thread timer = new Thread(new TimerTask());
        timer.setName("sentinel-timer-tick-task");
        timer.start();
    }

    static class TimerTask implements Runnable {
        @Override
        public void run() {
            long start = System.currentTimeMillis();
            System.out.println("Begin to run! Go go go!");
            System.out.println("See corresponding metrics.log for accurate statistic data");

            long oldTotal = 0;
            long oldPass = 0;
            long oldBlock = 0;

            // 每秒打印一次，清除掉上一秒统计的总数、通过次数、锁住的次数
            while (!stop) {
                sleep(1000);

                long globalTotal = TOTAL.get();
                long oneSecondTotal = globalTotal - oldTotal;
                oldTotal = globalTotal;

                long globalPass = PASS.get();
                long oneSecondPass = globalPass - oldPass;
                oldPass = globalPass;

                long globalBlock = BLOCK.get();
                long oneSecondBlock = globalBlock - oldBlock;
                oldBlock = globalBlock;

                System.out.println(TimeUtil.currentTimeMillis() + ", total:" + oneSecondTotal
                    + ", pass:" + oneSecondPass + ", block:" + oneSecondBlock);

                if (seconds-- <= 0) {
                    stop = true;
                }
            }

            long cost = System.currentTimeMillis() - start;
            System.out.println("time cost: " + cost + " ms");
            System.out.println("total: " + TOTAL.get() + ", pass:" + PASS.get()
                + ", block:" + BLOCK.get());
            System.exit(0);
        }
    }
}