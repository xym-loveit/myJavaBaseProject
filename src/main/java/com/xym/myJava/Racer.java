package com.xym.myJava;

public class Racer extends Thread {

    private FireFlag fireFlag;

    public Racer(FireFlag fireFlag) {
        this.fireFlag = fireFlag;
    }

    @Override
    public void run() {
        try {
            fireFlag.waitForFire();
            System.out.println("start run " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FireFlag fireFlag = new FireFlag();
        Racer[] racers = new Racer[10];
        for (int i = 0; i < 10; i++) {
            racers[i] = new Racer(fireFlag);
            racers[i].start();
        }
        Thread.sleep(1000);

        fireFlag.fire();
    }
}
