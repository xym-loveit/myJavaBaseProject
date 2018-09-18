package com.xym.myJava.lambda;

import java.util.concurrent.ForkJoinPool;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-18 15:23
 */
public class ForkJoinPoolDemo {
    public static void main(String[] args) {
        //可以通过JVM参数设置
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "5");
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());    // 并行线程池中线程个数，取决于CPU的物理核数
    }
}
