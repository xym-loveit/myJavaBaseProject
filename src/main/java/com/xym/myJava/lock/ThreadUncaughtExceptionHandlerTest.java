package com.xym.myJava.lock;

import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-16 23:06
 */
public class ThreadUncaughtExceptionHandlerTest {

    public static void main(String[] args) {


        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup.getName());
        System.out.println(threadGroup.getParent());
        System.out.println(threadGroup.getParent().getParent());

        /**
         * 设置线程执行出现异常时回调接口
         */
        //Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
        //    @Override
        //    public void uncaughtException(Thread t, Throwable e) {
        //        System.out.println("--->" + t);
        //        e.printStackTrace();
        //    }
        //});


        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //制造异常
                System.out.println(1 / 0);
            }
        }.start();

    }
}
