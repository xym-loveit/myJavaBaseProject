package com.xym.myJava;

/**
 * @author xym
 */
public class WaitThread extends Thread {

    private volatile boolean fire = Boolean.FALSE;

    @Override
    public void run() {
        try {
            synchronized (this) {
                System.out.println("~~~~~~~~~~~~~~~~--");
                while (!fire) {
                    System.out.println("wait--前");
                    wait();//进入条件等待队列
                    System.out.println("-------------------------重新竞争对象锁");
                }
            }
            System.out.println("-------------fired");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void fire() throws InterruptedException {
        this.fire = true;
        notify();//不会释放锁
        System.out.println("等待2秒释放锁...");
        Thread.sleep(1000 * 2);
    }

    public static void main(String[] args) throws InterruptedException {
        WaitThread waitThread = new WaitThread();
        waitThread.start();
        Thread.sleep(1000);
        System.out.println("fire");
        waitThread.fire();
    }

}
