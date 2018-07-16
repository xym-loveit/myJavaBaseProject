package com.xym.myJava;

public class aSnycCounter {

    private int counter;

    public synchronized void incr() {
        counter++;
    }

    public synchronized int getCounter() {
        return counter;
    }
}
