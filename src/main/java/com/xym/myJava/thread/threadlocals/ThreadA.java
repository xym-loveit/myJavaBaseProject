package com.xym.myJava.thread.threadlocals;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-25 11:_01
 */
public class ThreadA extends Thread {
    @Override
    public void run() {
        try {
            for (int s = 0; s < 10; s++) {
                System.out.println("在ThreadA线程中取值=" + Test01.Tools.date.get());
                System.out.println("在ThreadA线程中取值222=" + Test01.Tools.inheritableData.get());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
