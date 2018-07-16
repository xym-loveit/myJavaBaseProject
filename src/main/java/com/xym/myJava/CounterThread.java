package com.xym.myJava;

public class CounterThread extends Thread {

    private static int counter = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int c = 1000;
        CounterThread[] counterThreads = new CounterThread[c];
        for (int i = 0; i < c; i++) {
            counterThreads[i] = new CounterThread();
            counterThreads[i].start();
        }

        for (int i = 0; i < c; i++) {
            counterThreads[i].join();
        }

        System.out.println(counter);
    }
}
