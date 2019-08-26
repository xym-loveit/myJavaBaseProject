package com.xym.myJava.thread.cnblogs_skywang12345.chapter1;

// Demo1.java的源码
class MyThread4 extends Thread {

    public MyThread4(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (!isInterrupted()) {
                Thread.sleep(50); // 休眠100ms
                i++;
                System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") loop " + i);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") catch InterruptedException.");
        }
        System.out.println("over exit");
    }
}

public class Demo1 {

    public static void main(String[] args) {
        try {
            Thread t1 = new MyThread4("t1");  // 新建“线程t1”
            System.out.println(t1.getName() + " (" + t1.getState() + ") is new.");

            t1.start();                      // 启动“线程t1”
            System.out.println(t1.getName() + " (" + t1.getState() + ") is started.");

            // 主线程休眠300ms，然后主线程给t1发“中断”指令。
            Thread.sleep(51);
            t1.interrupt();
            System.out.println(t1.getName() + " (" + t1.getState() + ") is interrupted.");

            // 主线程休眠300ms，然后查看t1的状态。
            Thread.sleep(300);
            System.out.println(t1.getName() + " (" + t1.getState() + ") is interrupted now.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}