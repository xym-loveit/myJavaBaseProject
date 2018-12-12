package com.xym.myJava.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 事件生产工厂：生产事件存入ringbuffer中
 *
 * @author xym
 * @create 2018-12-12 9:46
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }

}
