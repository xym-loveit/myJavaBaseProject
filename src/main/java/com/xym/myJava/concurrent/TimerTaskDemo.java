package com.xym.myJava.concurrent;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 使用timer的时候，如果有一个任务抛出异常，则其他的任务也将终止执行
 * <p>
 * timer task内部使用的是单个消费线程，如果抛出异常，最好在外层捕获处理
 * <p>
 * 像这样的延迟任务最好使用ScheduledExecutorService进行替代，这样不会出现一个任务出现问题的时候影响了其他任务的执行
 *
 * @author xym
 * @create 2019-03-21 16:39
 */
public class TimerTaskDemo {

    static Timer timer = new Timer("task manager");

    public static void main(String[] args) {

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("--task one--");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    throw new RuntimeException("error ");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }, 500);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (; ; ) {
                    System.out.println("--task two--");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 1000);
    }
}
