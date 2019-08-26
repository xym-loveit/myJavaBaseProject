package com.xym.myJava.thread.cnblogs_skywang12345.chapter2;

class CounterClient implements Runnable {
    private Counter c;
    private int num;

    public CounterClient(Counter c, int num) {
        this.c = c;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            c.increment();
            //System.out.println(Thread.currentThread().getName());
        }
    }
}