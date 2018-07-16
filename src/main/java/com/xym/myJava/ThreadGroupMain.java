package com.xym.myJava;

/**
 * @author xym
 */
public class ThreadGroupMain implements Runnable {
    public static void main(String[] args) {
        ThreadGroup printGroup = new ThreadGroup("PrintGroup");
        Thread t1 = new Thread(printGroup, new ThreadGroupMain(), "T1");
        Thread t2 = new Thread(printGroup, new ThreadGroupMain(), "T2");
        t1.start();
        t2.start();
        System.out.println(printGroup.activeCount());
        printGroup.list();
    }

    @Override
    public void run() {
        String groupName = Thread.currentThread().getThreadGroup().getName() + "-" + Thread.currentThread().getName();
        while (true) {
            System.out.println("I am " + groupName);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
