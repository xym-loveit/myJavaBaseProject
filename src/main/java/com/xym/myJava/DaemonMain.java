package com.xym.myJava;

/**
 * 守护线程因用户线程存在而存在
 *
 * @author xym
 */
public class DaemonMain {

    public static class DaemonT extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DaemonT daemonT = new DaemonT();
//        daemonT.setDaemon(true);
        daemonT.start();

        Thread.sleep(2000);
    }
}
