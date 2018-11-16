package com.xym.myJava;

/**
 * 打印最普通的方法，使用了多少个线程
 *
 * @author xym
 * @create 2018-10-12 10:13
 */
public class TestMain2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello world");
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
        int nowThreads = topGroup.activeCount();
        Thread[] lstThreads = new Thread[nowThreads];
        topGroup.enumerate(lstThreads);
        for (int i = 0; i < nowThreads; i++) {
            System.out.println("线程number：" + i + " = " + lstThreads[i].getName());
        }
    }
}
