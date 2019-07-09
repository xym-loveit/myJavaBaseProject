package com.xym.myJava.jdk8.collect;

import java.util.function.Function;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-09 11:33
 */
public class PerformanceDemo {
    public static void main(String[] args) {
      /*  System.out.println("Sequential sum done in:" +
                measureSumPerf(ParallelStreams::sequentialSum, 10_000_000) + " msecs");
        System.out.println("Iterative sum done in:" +
                measureSumPerf(ParallelStreams::iterativeSum, 10_000_000) + " msecs");
        System.out.println("Parallel sum done in: " +
                measureSumPerf(ParallelStreams::parallelSum, 10_000_000) + " msecs");
        System.out.println("rangedSum sum done in: " +
                measureSumPerf(ParallelStreams::rangedSum, 10_000_000) + " msecs");
        System.out.println("Parallel range sum done in: " +
                measureSumPerf(ParallelStreams::parallelRangedSum, 10_000_000) + " msecs");*/
        //System.out.println("sideEffectSum range sum done in: " +
        //        measureSumPerf(ParallelStreams::sideEffectSum, 10_000_000) + " msecs");
        System.out.println("sideEffectParallelSum range sum done in: " +
                measureSumPerf(ParallelStreams::sideEffectParallelSum, 10_000_000) + " msecs");
        System.out.println("sideEffectParallelSum range sum done in: " +
                measureSumPerf(ParallelStreams::sideEffectParallelSumV2, 10_000_000) + " msecs");
        System.out.println("forkJoinSum sum done in: " +
                measureSumPerf(ParallelStreams::forkJoinSum, 10_000_000) + " msecs");
    }

    /**
     * 对被测试方法，进行n次的结果采集测试
     *
     * @param adder
     * @param n
     * @return
     */
    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }
}
