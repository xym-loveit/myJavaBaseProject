package com.xym.myJava;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 使用Fork/Join框架来计算数列求和
 *
 * @author xym
 */
public class CountTaskDemo extends RecursiveTask<Long> {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTaskDemo countTaskDemo = new CountTaskDemo(0, 200000);
        ForkJoinTask<Long> result = forkJoinPool.submit(countTaskDemo);

        Long aLong = result.get();
        System.out.println("sum=" + aLong);
    }

    private static final int THRESHOLD = 10000;

    private long start;
    private long end;

    public CountTaskDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    static int count = 1;

    @Override
    protected Long compute() {
        System.out.println("-----------------"+count++);
        long sum = 0;
        boolean canCompute = (end - start) < THRESHOLD;
        if (canCompute) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            System.out.println("--->"+sum);
        } else {
            //分成100个小任务
            long step = (start + end) / 100;
            System.out.println("-------------------->"+step);
            ArrayList<CountTaskDemo> countTaskDemos = new ArrayList<>();
            long pos = start;
            for (int i = 0; i < 100; i++) {
                long lastOne = pos + step;
                if (lastOne > end) {
                    lastOne = end;
                }

                CountTaskDemo countTaskDemo = new CountTaskDemo(pos, lastOne);
                pos = (pos + step + 1);
                countTaskDemos.add(countTaskDemo);
                countTaskDemo.fork();
            }

            for (CountTaskDemo countTaskDemo : countTaskDemos) {
                sum += countTaskDemo.join();
            }
        }
        return sum;
    }
}
