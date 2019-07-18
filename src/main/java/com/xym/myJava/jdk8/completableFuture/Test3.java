package com.xym.myJava.jdk8.completableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * 计算结果完成时的处理
 *
 * @author xym
 * @create 2019-07-17 11:20
 */
public class Test3 {
    private static Random random = new Random();
    public static final long t = System.currentTimeMillis();
    private static Thread thread;

    static int getMoreData() {
        System.out.println("begin to start compute");
        try {
            thread = Thread.currentThread();
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t) / 1000 + " seconds");
        return random.nextInt(1000);
    }

    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Test3::getMoreData);
        //计算结果完成时的处理,v表示正常结果，e为异常
        //Future<Integer> normal = future.whenComplete((v, e) -> {
        //    System.out.println(v);
        //    System.out.println(e);
        //});

        //前一个完成后继续执行
        //它们与handle方法的区别在于handle方法会处理正常计算值和异常，因此它可以屏蔽异常，避免异常继续抛出。
        // 而thenApply方法只是用来处理正常值，因此一旦有异常就会抛出。
        //CompletableFuture<String> thenApply = future.thenApply((v) -> {
        //    return String.valueOf(v) + "success";
        //});
        //System.out.println(thread);
        //System.out.println(thenApply.get());
        //Thread.sleep(2000);
        //消费型
        //future.thenAcceptBoth(CompletableFuture.completedFuture("ssss"), (v1, v2) -> {
        //    System.out.println("v1=" + v1 + ",v2=" + v2);
        //});
        //future.runAfterBoth(CompletableFuture.completedFuture("ssss"), () -> {
        //    System.out.println("-------------------");
        //});


        System.in.read();
    }
}
