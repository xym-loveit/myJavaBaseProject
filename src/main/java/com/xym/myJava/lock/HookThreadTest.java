package com.xym.myJava.lock;

import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-16 23:15
 */
public class HookThreadTest {
    public static void main(String[] args) {
        //为应用程序注入钩子线程
        Runtime.getRuntime().addShutdownHook(new Thread("hook1") {
            @Override
            public void run() {
                System.out.println("the hook thread 1 is running");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("the hook thread 1 will exit");
            }
        });

        //为应用程序注入钩子线程
        Runtime.getRuntime().addShutdownHook(new Thread("hook2") {
            @Override
            public void run() {
                System.out.println("the hook thread 2 is running");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("the hook thread 2 will exit");
            }
        });

        System.out.println("the program will is stopping! ");
    }
}
