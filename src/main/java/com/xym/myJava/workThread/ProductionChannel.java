package com.xym.myJava.workThread;


/**
 * 传送带
 * <p>
 * 产品传送带，在传送带上除了负责产品加工的工人之外，还有在传送带上等待加工的产品
 *
 * @author xym
 * @create 2018-07-19 17:06
 */
public class ProductionChannel {

    /**
     * 传送带上最多可以有多少个待加工的产品
     */
    private final static int MAX_PROD = 100;

    /**
     * 主要用来存放待加工的产品，也就是传送带
     */
    private final Production[] productionQueue;

    /**
     * 队列尾
     */
    private int tail;
    /**
     * 队列头
     */
    private int head;

    /**
     * 当前流水线上有多少代加工的产品
     */
    private int total;

    private final Worker[] workers;

    /**
     * 创建ProductionChannel时应指定需要多少个流水线工人
     *
     * @param workSize
     */
    public ProductionChannel(int workSize) {
        this.workers = new Worker[workSize];
        this.productionQueue = new Production[MAX_PROD];
        for (int i = 0; i < workSize; i++) {
            workers[i] = new Worker("worker-" + i, this);
            workers[i].start();
        }
    }

    /**
     * 接受来自上游的半成品
     */
    public void offerProduction(Production production) {
        synchronized (this) {
            /**
             * 当传送带上代加工的产品超过了最大值时，需要阻塞上游再次传送产品
             */
            while (total >= productionQueue.length) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //将产品放到传送带上，并且通知工人线程工作
            productionQueue[tail] = production;
            tail = (tail + 1) % productionQueue.length;
            total++;
            this.notifyAll();
        }
    }

    /**
     * 工人线程worker 从传送带上获取产品，并且加工
     *
     * @return
     */
    public Production takeProduction() {
        synchronized (this) {
            while (total <= 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //获取产品
            Production production = productionQueue[head];
            head = (head + 1) % productionQueue.length;
            total--;
            this.notifyAll();
            return production;
        }
    }

}
