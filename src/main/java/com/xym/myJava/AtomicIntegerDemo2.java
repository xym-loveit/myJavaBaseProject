package com.xym.myJava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicIntegerDemo2 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

       /* IntStream.range(0, 1000).forEach(i -> executorService.submit(() -> {
            System.out.print(i+"\t");
            atomicInteger.incrementAndGet();
        }));

        ConcurrentUtils.stop(executorService);
        System.out.println(atomicInteger.get());*/

        IntStream.range(0, 1000).forEach(i -> {
            Runnable task = () -> atomicInteger.updateAndGet(n -> n + 2);
            executorService.submit(task);
        });

        ConcurrentUtils.stop(executorService);
        System.out.println(atomicInteger.get());
    }
}
