package com.xym.myJava.jdk8.completableFuture;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-17 22:30
 */
public class Test6 {
    public static void main(String[] args) {
        //CompletableFuture<Void> future = CompletableFuture
        //        //委托师傅做蛋糕
        //        .supplyAsync(() -> {
        //            try {
        //                System.out.println("师傅准备做蛋糕");
        //                TimeUnit.SECONDS.sleep(1);
        //                System.out.println("师傅做蛋糕做好了");
        //            } catch (InterruptedException e) {
        //                e.printStackTrace();
        //            }
        //            return "cake";
        //        })
        //        //做好了告诉我一声
        //        .thenAccept(cake -> {
        //            System.out.println("我吃蛋糕:" + cake);
        //        });
        //System.out.println("我先去喝杯牛奶");
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
