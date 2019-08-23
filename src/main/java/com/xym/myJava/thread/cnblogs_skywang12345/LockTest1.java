package com.xym.myJava.thread.cnblogs_skywang12345;

// LockTest1.java的源码
class Something {
    public synchronized void isSyncA() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName() + " : isSyncA");
            }
        } catch (InterruptedException ie) {
        }
    }

    public synchronized void isSyncB() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName() + " : isSyncB");
            }
        } catch (InterruptedException ie) {
        }
    }

    public static synchronized void cSyncA() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName() + " : cSyncA");
            }
        } catch (InterruptedException ie) {
        }
    }

    public static void cSyncB() {
        try {
            synchronized (Something.class) {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " : cSyncB");
                }
            }
        } catch (InterruptedException ie) {
        }

    }
}

public class LockTest1 {

    Something x = new Something();
    Something y = new Something();

    // 比较(01) x.isSyncA()与x.isSyncB() 
    private void test1() {
        // 新建t11, t11会调用 x.isSyncA()
        Thread t11 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        x.isSyncA();
                    }
                }, "t11");

        // 新建t12, t12会调用 x.isSyncB()
        Thread t12 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        x.isSyncB();
                    }
                }, "t12");


        t11.start();  // 启动t11
        t12.start();  // 启动t12
    }


    // 比较(02) x.isSyncA()与y.isSyncA()
    private void test2() {
        // 新建t21, t21会调用 x.isSyncA()
        Thread t21 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        x.isSyncA();
                    }
                }, "t21");

        // 新建t22, t22会调用 x.isSyncB()
        Thread t22 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        y.isSyncA();
                    }
                }, "t22");


        t21.start();  // 启动t21
        t22.start();  // 启动t22
    }

    // 比较(03) x.cSyncA()与y.cSyncB()
    private void test3() {
        // 新建t31, t31会调用 x.isSyncA()
        Thread t31 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        x.cSyncA();
                    }
                }, "t31");

        // 新建t32, t32会调用 x.isSyncB()
        Thread t32 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        y.cSyncB();
                    }
                }, "t32");


        t31.start();  // 启动t31
        t32.start();  // 启动t32
    }

    // 比较(04) x.isSyncA()与Something.cSyncA()
    private void test4() {
        // 新建t41, t41会调用 x.isSyncA()
        Thread t41 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        x.isSyncA();
                    }
                }, "t41");

        // 新建t42, t42会调用 x.isSyncB()
        Thread t42 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Something.cSyncA();
                    }
                }, "t42");


        t41.start();  // 启动t41
        t42.start();  // 启动t42
    }

    public static void main(String[] args) {
        LockTest1 demo = new LockTest1();
        //demo.test1();
        //demo.test2();
        //demo.test3();
        demo.test4();
    }
}