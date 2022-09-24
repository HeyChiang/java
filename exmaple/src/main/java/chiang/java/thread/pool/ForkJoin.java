package chiang.java.thread.pool;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinTask用来将任务拆分成小块计算
 * @author Chiang
 */
public class ForkJoin {
    static Random random = new Random(0);

    public static void main(String[] args) throws Exception {
        // 创建2000个随机数组成的数组:
        long[] array = new long[20000];
        long expectedSum = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100000);
            expectedSum += array[i];
        }
        System.out.println("Expected sum: " + expectedSum);

        printNormalResult(array);
        printFokJoinResult(array);
    }

    /**
     * 打印正常执行累加需要消耗的时间和结果
     */
    private static void printNormalResult(long[] array) {
        long startTime = System.currentTimeMillis();
        long expectedSum =0;
        for (int i = 0; i < array.length; i++) {
            expectedSum += array[i];
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Normal sum: " + expectedSum + " in " + (endTime - startTime) + " ms.");
    }

    /**
     * 打印结果forkjoin执行花费的时间和结果
     */
    private static void printFokJoinResult(long[] array) {
        ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
        long startTime = System.currentTimeMillis();
        Long result = ForkJoinPool.commonPool().invoke(task);
        long endTime = System.currentTimeMillis();

        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }


}

class SumTask extends RecursiveTask<Long> {
    static final int THRESHOLD = 100000;
    long[] array;
    int start;
    int end;

    SumTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // 如果任务足够小,直接计算:
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += this.array[i];
            }
            return sum;
        }
        // 任务太大,一分为二:
        int middle = (end + start) / 2;
//        System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
        SumTask subtask1 = new SumTask(this.array, start, middle);
        SumTask subtask2 = new SumTask(this.array, middle, end);
        invokeAll(subtask1, subtask2);
        Long subresult1 = subtask1.join();
        Long subresult2 = subtask2.join();
        Long result = subresult1 + subresult2;
        System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result);
        return result;
    }
}