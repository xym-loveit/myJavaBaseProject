package com.xym.myJava;

public class FireFlag {
    private volatile boolean fire = false;

    public synchronized void waitForFire() throws InterruptedException {
        while (!fire) {
            wait();
        }
    }

    public synchronized void fire() {
        this.fire = true;
        notifyAll();
    }
}
