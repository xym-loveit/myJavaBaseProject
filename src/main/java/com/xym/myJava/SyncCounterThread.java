package com.xym.myJava;

public class SyncCounterThread extends Thread {

    private aSnycCounter aSnycCounter;

    public SyncCounterThread(com.xym.myJava.aSnycCounter aSnycCounter) {
        this.aSnycCounter = aSnycCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            aSnycCounter.incr();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        com.xym.myJava.aSnycCounter aSnycCounter = new aSnycCounter();
        SyncCounterThread[] syncCounterThreads = new SyncCounterThread[1000];
        for (int i = 0; i < 1000; i++) {
            syncCounterThreads[i] = new SyncCounterThread(aSnycCounter);
            syncCounterThreads[i].start();
        }

        for (int i = 0; i < 1000; i++) {
            syncCounterThreads[i].join();
        }

        System.out.println("count=" + aSnycCounter.getCounter());
    }
}
