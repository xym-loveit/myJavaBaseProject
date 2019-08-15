package com.xym.myJava.jmx.mxbeans;

import java.beans.ConstructorProperties;
import java.util.Date;

/**
 * MXBean框架会调用QueueSample中的所有getters，并将返回的实例转化为一个 CompositeData
 * 实例，然后使用@ConstructorProperties注解从一个CompositeData实例中重构一个 QueueSample 实例
 *
 * @author xym
 * @create 2019-08-15 15:09
 */
public class QueueSample {
    private final Date date;
    private final int size;
    private final String head;

    @ConstructorProperties({"date", "size", "head"})
    public QueueSample(Date date, int size, String head) {
        this.date = date;
        this.size = size;
        this.head = head;
    }

    public Date getDate() {
        return date;
    }

    public int getSize() {
        return size;
    }

    public String getHead() {
        return head;
    }
}
