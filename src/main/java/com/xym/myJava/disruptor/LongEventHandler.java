package com.xym.myJava.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * 事件处理器，也就是消费者，就是将事件的值打印出来
 *
 * @author xym
 * @create 2018-12-12 9:49
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Event：" + event.getValue() + ",sequence=" + sequence + ",endOfBatch=" + endOfBatch);
    }
}
