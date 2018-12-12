package com.xym.myJava.disruptor;

/**
 * 生产者和消费者共同要处理的队列数据
 * <p>
 * 向ringbuffer中插入的事件元素：就是在对象中放了一个long变量
 *
 * @author xym
 * @create 2018-12-12 9:43
 */
public class LongEvent {

    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}
