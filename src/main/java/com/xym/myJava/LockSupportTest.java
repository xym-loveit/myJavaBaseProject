package com.xym.myJava;

import java.util.concurrent.locks.LockSupport;

/**
 * park使得当前线程放弃CPU，进入等待状态（WAITING），操作系统不再对它进行调度，
 * <p>
 * 什么时候再调度呢？有其他线程对它调用了unpark，unpark使参数指定的线程恢复可运行状态
 *
 * @author xym
 */
public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("entry");
                LockSupport.park();//放弃CPU,等待unpark唤醒
                System.out.println("exit");
            }
        };

        thread.start();
        Thread.sleep(5 * 1000);
//        System.out.println(  LockSupport.getBlocker(thread));
        LockSupport.unpark(thread);
    }
}
