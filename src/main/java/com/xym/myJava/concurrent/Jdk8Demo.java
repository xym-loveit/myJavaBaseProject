package com.xym.myJava.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-14 15:05
 */
public class Jdk8Demo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(() -> "task1", () -> "task2", () -> "task3");
        try {
            //invokeAll批量提交多个
            executorService.invokeAll(callables).stream().map(future -> {
                try {
                    return future.get();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new IllegalStateException(e);
                }
            }).forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
