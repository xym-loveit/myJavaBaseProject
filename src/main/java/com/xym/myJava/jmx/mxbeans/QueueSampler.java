package com.xym.myJava.jmx.mxbeans;

import java.util.Date;
import java.util.Queue;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-15 15:14
 */
public class QueueSampler implements QueueSamplerMXBean {

    private Queue<String> queue;
    private QueueSample queueSample;

    public QueueSampler(Queue<String> queue) {
        this.queue = queue;
        this.queueSample = new QueueSample(new Date(), queue.size(), queue.peek());
    }

    @Override
    public QueueSample getQueueSample() {
        return queueSample;
    }

    @Override
    public void setQueueSample(QueueSample queueSample) {
        this.queueSample = queueSample;
    }

    @Override
    public void clearQueue() {
        synchronized (queue) {
            queue.clear();
        }
    }
}
