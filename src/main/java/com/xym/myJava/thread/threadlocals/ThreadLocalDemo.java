package com.xym.myJava.thread.threadlocals;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 演示threadlocal使用不当，导致的内存泄露问题
 *
 * @author xym
 * @create 2019-03-21 22:28
 */
public class ThreadLocalDemo {
    final static ThreadLocal<LocalVariable> THREAD_LOCAL = new ThreadLocal<>();
    final static ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    static class LocalVariable {
        private Long[] a = new Long[1024 * 1024];
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            EXECUTOR.execute(() -> {
                THREAD_LOCAL.set(new LocalVariable());
                System.out.println("use local variable");
                //注释掉remove方法调用，发现内存泄露
                //THREAD_LOCAL.remove();
            });
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println("pool execute over.");
    }
}
