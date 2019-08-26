package com.xym.myJava.thread.cnblogs_skywang12345.chapter2;

class SyncCounter implements Counter {
    private long counter = 0;

    @Override
    public synchronized void increment() {
        counter++;
    }

    @Override
    public long getCounter() {
        return counter;
    }
}