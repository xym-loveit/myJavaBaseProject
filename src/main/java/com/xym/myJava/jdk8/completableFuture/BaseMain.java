package com.xym.myJava.jdk8.completableFuture;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-17 10:53
 */
public class BaseMain {

    public static CompletableFuture<Integer> compute() {
        CompletableFuture future = new CompletableFuture();
        return future;
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        final CompletableFuture<Integer> f = compute();
        class Client extends Thread {
            private CompletableFuture future;

            public Client(String name, CompletableFuture future) {
                super(name);
                this.future = future;
            }

            @Override
            public void run() {
                try {
                    //get方法会阻塞，直到设置值后
                    System.out.println(this.getName() + ": " + f.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

        //new Client("Client1", f).start();
        //new Client("Client2", f).start();
        //System.out.println("waiting");
        ////放在本线程中直接执行
        //f.complete(100);

        //返回一个已经计算好的CompletableFuture
        //CompletableFuture<Object> nullResult = CompletableFuture.completedFuture(null);
        //System.out.println(nullResult.get());

        //无返回值,且使用ForkJoinPool.commonPool线程池
        //CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
        //    try {
        //        Thread.sleep(1000);
        //        System.out.println(Thread.currentThread());
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //});
        //ExecutorService executor = Executors.newFixedThreadPool(2, new ThreadFactory() {
        //    @Override
        //    public Thread newThread(Runnable r) {
        //        Thread thread = new Thread(r);
        //        thread.setDaemon(true);
        //        return thread;
        //    }
        //});
        //CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
        //    try {
        //        Thread.sleep(1000);
        //        System.out.println(Thread.currentThread());
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //}, executor);
        //runAsync.get()
        //System.out.println(future.get());
        //System.out.println(runAsync.get());
        //通过函数式
        //CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
        //    try {
        //        Thread.sleep(1000);
        //        System.out.println(Thread.currentThread());
        //        return "success";
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //    return null;
        //});
        //System.out.println(supplyAsync.get());
        System.in.read();
    }
}
