package com.xym.myJava.thread;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-22 10:50
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 500000; i++) {
            if (Thread.interrupted()) {
                System.out.println("已经是停止状态了!我要退出了!" + Thread.interrupted());
                //System.out.println();
                //break;//只能终止循环，并不能停止线程
                return;
            }
            System.out.println("i=" + (i + 1));
        }
        System.out.println("看到这句话说明线程并未终止------");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        try {
            Thread.sleep(2000);
            myThread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
    }
}
