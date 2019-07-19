package com.xym.myJava.jdk8.completableFuture;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * 线程池里面线程是否为daemon直接影响程序的执行
 * 如果是daemon则主线程运行完毕即会退出
 *
 * @author xym
 * @create 2019-07-19 10:14
 */
public class Main {
    private static final ThreadPoolExecutor executorService =
            new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                    new BasicThreadFactory.Builder().namingPattern("pushOrder-Statu-pool-%d").daemon(false).build());

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().isDaemon());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("---------------");
        }, executorService);
        future.whenComplete((v, t) -> {
            System.out.println("over");
        });

        System.in.read();
    }
}
