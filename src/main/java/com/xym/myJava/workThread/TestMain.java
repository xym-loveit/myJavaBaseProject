package com.xym.myJava.workThread;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * worker-thread设计模式
 *
 * @author xym
 * @create 2018-07-19 17:34
 */
public class TestMain {

    public static void main(String[] args) {
        //流水线上有5个工人
        ProductionChannel productionChannel = new ProductionChannel(5);
        AtomicInteger prodNo = new AtomicInteger();
        //流水线上有8个工人往传送带上不断的放置等待加工的半成品
        for (int i = 0; i < 8; i++) {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        productionChannel.offerProduction(new Production(prodNo.getAndIncrement()));
                        try {
                            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
    }

}
