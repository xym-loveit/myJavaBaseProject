package com.xym.myJava.jdk8.collect;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-09 11:36
 */
public class ParallelStreams {


    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    /**
     * 顺序求和
     *
     * @param
     * @return
     */
    public static long sequentialSum(long num) {
        return Stream.iterate(1L, n -> n + 1).limit(num).reduce(0L, Long::sum);
    }

    /**
     * 原始循坏
     *
     * @param n
     * @return
     */
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    /**
     * 并行求和
     *
     * @param n
     * @return
     */
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }


    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    /**
     * 并行化问题
     *
     * @param n
     * @return
     */
    public static long sideEffectParallelSum(long n) {
        /**
         * 采用并行化共享变量引起数据不准确问题
         */
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    /**
     * 采用原子类作为累加器的变量
     *
     * @param n
     * @return
     */
    public static long sideEffectParallelSumV2(long n) {
        /**
         * 采用并行化共享变量引起数据不准确问题
         */
        AtomicAccumulator accumulator = new AtomicAccumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total.get();
    }

    /**
     * 累加器类
     */
    public static class Accumulator {
        public long total = 0;

        public void add(long value) {
            total += value;
        }
    }

    /**
     * 累加器类
     */
    public static class AtomicAccumulator {
        public AtomicLong total = new AtomicLong(0);

        public void add(long value) {
            total.addAndGet(value);
        }
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

}
