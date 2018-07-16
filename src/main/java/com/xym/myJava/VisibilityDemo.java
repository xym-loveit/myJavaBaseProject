package com.xym.myJava;

import java.util.Date;

public class VisibilityDemo {

    private static boolean shutdown = false;

    static class HelloThread extends Thread {
        @Override
        public void run() {
            while (!shutdown) {
                System.out.println("------------------" + new Date());
            }
            System.out.println("exit hello");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new HelloThread().start();
        Thread.sleep(100);
        shutdown = true;
        System.out.println("exit main");
    }

}
