package com.xym.myJava.jdk8.completableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-17 11:20
 */
public class Test2 {
    private static Random random = new Random();
    public static final long t = System.currentTimeMillis();
    private static Thread thread;

    static int getMoreData() {
        System.out.println("begin to start compute");
        try {
            thread = Thread.currentThread();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t) / 1000 + " seconds");
        return random.nextInt(1000);
    }

    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Test2::getMoreData);
        //计算结果完成时的处理,v表示正常结果，e为异常
        Future<Integer> normal = future.whenComplete((v, e) -> {
            System.out.println(v);
            System.out.println(e);
        });

        //exceptionally方法返回一个新的CompletableFuture，当原始的CompletableFuture抛出异常的时候，
        // 就会触发这个CompletableFuture的计算，调用function计算值，否则如果原始的CompletableFuture正常计算完后，
        // 这个新的CompletableFuture也计算完成，它的值和原始的CompletableFuture的计算的值相同。
        // 也就是这个exceptionally方法用来处理异常的情况。
        //CompletableFuture<Integer> exceptionally = future.exceptionally((e) -> {
        //    System.out.println("-----"+e.getMessage());
        //    return 1111;
        //});

        //System.out.println(f.get());
        //主线程休眠2秒中断线程池Thread[ForkJoinPool.commonPool-worker-1,5,main]中的执行线程
        //Thread.sleep(2000);
        //System.out.println(thread);
        //thread.interrupt();
        ////System.out.println("normal=" + normal.get());
        //System.out.println("exceptionally=" + exceptionally.get());
        //System.in.read();
    }
}
