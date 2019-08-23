package com.xym.myJava.thread.cnblogs_skywang12345;

// SleepTest.java的源码
class ThreadA3 extends Thread {
    public ThreadA3(String name) {
        super(name);
    }

    @Override
    public void run() {
        synchronized (SleepTest.lock) {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.printf("%s: %d\n", this.getName(), i);
                    // i能被4整除时，休眠100毫秒
                    if (i % 4 == 0) {
                        //yield是从运行状态到就绪状态，理论是给比他优先级更高/平级-的线程执行机会但是没有保证；运行-->就绪
                        // sleep是从运行状态到阻塞状态，直到堵塞时间结束，线程从堵塞---->就绪；运行-->堵塞-->就绪
                        //相同点：2者都不会释放监视器锁
                        /************************************************************************/
                        //wait首先是object类的成员方法，而sleep是thread类的静态方法
                        //wait调用之后会释放监视器锁，而sleep不会释放锁
                        //wait必须要在同步方法之内调用（调用wait时候必须拿到监视器锁），sleep则可以随便调用
                        //调用wait()(无参数)后，必须要使用notify/notifyall来唤醒,并且只有拿到锁后wait才会进入就绪状态，
                        //而sleep则阻塞时间结束后自动进入就绪状态
                        Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class SleepTest {

    static Object lock = new Object();

    public static void main(String[] args) {
        ThreadA3 t1 = new ThreadA3("t1");
        ThreadA3 t2 = new ThreadA3("t2");
        t1.start();
        t2.start();
    }
}