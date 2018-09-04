package com.xym.myJava.schedule;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 13:51
 */
public class OrderDelay implements Delayed {

    //订单号
    private String orderId;
    //超时时间
    private long timeout;

    public OrderDelay(String orderId, long timeout) {
        this.orderId = orderId;
        this.timeout = timeout + System.nanoTime();
    }

    // 返回距离你自定义的超时时间还有多少
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((timeout - System.nanoTime()), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        if (other == this) {
            return 0;
        }

        OrderDelay orderDelay = (OrderDelay) other;
        long d = (getDelay(TimeUnit.NANOSECONDS) - orderDelay
                .getDelay(TimeUnit.NANOSECONDS));

        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }

    void print() {
        System.out.println(orderId + "编号的订单要删除啦。。。。");
    }
}
