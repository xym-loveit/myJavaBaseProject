package com.xym.myJava;

public class DeadLockDemo {

    private static Object lockA = new Object();
    private static Object lockB = new Object();

    private static void startThreadA() {
        Thread threadA = new Thread() {
            @Override
            public void run() {
                synchronized (lockA) {
                    try {
                        System.out.println("A------------锁定A");
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (lockB) {
                    System.out.println("A---------------锁定B");
                }
            }
        };
        threadA.start();
    }

    private static void startThreadB() {
        Thread threadA = new Thread() {
            @Override
            public void run() {
                synchronized (lockB) {
                    System.out.println("B--------锁定B");
                }
                synchronized (lockA) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("B-------------锁定A");
                }
            }
        };
        threadA.start();
    }

    public static void main(String[] args) {
        startThreadA();
        startThreadB();
    }
}
