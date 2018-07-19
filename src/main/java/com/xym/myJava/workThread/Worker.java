package com.xym.myJava.workThread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-19 17:19
 */
public class Worker extends Thread {

    /**
     * 传送带
     */
    private final ProductionChannel channel;

    //主要用于获取一个随机值，模拟加工一个产品需要消耗一定的时间，当然每个工人操作时，所花费的时间可能也不一样
    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public Worker(String workerName, ProductionChannel channel) {
        super(workerName);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Production production = channel.takeProduction();
                System.out.println(getName() + " process the " + production);
                production.create();
                
                TimeUnit.SECONDS.sleep(RANDOM.nextInt(10));
            } catch (InterruptedException e) {
            }
        }
    }
}
