package com.xym.myJava.jmx.mxbeans;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-15 15:08
 */
public interface QueueSamplerMXBean {
    QueueSample getQueueSample();

    void setQueueSample(QueueSample queueSample);

    void clearQueue();
}
