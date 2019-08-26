package com.xym.myJava.thread.cnblogs_skywang12345.chapter2;

class StupidCounter implements Counter {

    private long counter = 0;

    @Override
    public void increment() {
        counter++;
    }

    @Override
    public long getCounter() {
        return counter;
    }
}