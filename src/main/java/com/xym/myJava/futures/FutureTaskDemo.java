package com.xym.myJava.futures;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-03-13 9:31
 */
public class FutureTaskDemo {
    public static void main(String[] args) {
        Callable callable = () -> {
            TimeUnit.SECONDS.sleep(5);
            return "hello callable";
        };
        FutureTask task = new FutureTask<>(callable);
        new Thread(task, "t1").start();

        new Thread(() -> {
            try {
                System.out.println("等待任务t1返回");
                System.out.println(task.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
