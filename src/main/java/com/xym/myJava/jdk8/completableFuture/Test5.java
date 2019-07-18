package com.xym.myJava.jdk8.completableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * applyToEither方法是当任意一个CompletionStage完成的时候，fn会被执行，它的返回值会当作新的CompletableFuture<U>的计算结果
 *
 * @author xym
 * @create 2019-07-17 22:03
 */
public class Test5 {
    public static void main(String[] args) {
        Random rand = new Random();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "success";
        });

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future, future2);
        System.out.println(anyOf.join());
    }


}
