package com.xym.myJava.lock;

/**
 * 当前线程调用共享变量的wait方法后只会释放当前共享变量上的锁，如果当前线程还持有其他共享变量的锁
 * 则这些锁不会被释放
 *
 * @author xym
 * @create 2019-03-13 11:08
 */
public class MutexLock {
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("ThreadA get resourceA lock.");
                synchronized (resourceB) {
                    System.out.println("ThreadA get resourceB lock.");
                    System.out.println("线程A阻塞并释放resourceA的锁");
                    try {
                        //此时只会释放在共享变量resourceA上面的锁哦，resourceB上的锁并不会释放，所以线程B无法获取到resourceB
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }).start();


        new Thread(() -> {

            try {
                //让线程A有机会获取到resourceA锁
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (resourceA) {
                System.out.println("ThreadB get resourceA lock.");
                System.out.println("ThreadB try get resourceB lock...");
                synchronized (resourceB) {
                    System.out.println("ThreadB get resourceB lock.");
                    System.out.println("线程B阻塞并释放resourceA的锁");
                    try {
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }).start();


    }
}
