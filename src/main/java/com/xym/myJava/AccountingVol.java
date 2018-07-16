package com.xym.myJava;

/**
 * volatile只能解决可见性，不能保证多线程写的安全性
 *
 * @author xym
 */
public class AccountingVol implements Runnable {

    static AccountingVol accountingVol = new AccountingVol();

    static volatile int i = 0;

    public static /*synchronized*/ void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int i1 = 0; i1 < 10000000; i1++) {
//            synchronized (accountingVol) {
            increase();
//            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(accountingVol);
        Thread thread2 = new Thread(accountingVol);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(i);

    }
}
