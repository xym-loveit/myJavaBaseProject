package com.xym.myJava.observer.v4;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 11:29
 */
public class Father02 implements WakeUpListener {
    @Override
    public void actiontoWakenUp(WakeUpEvent02 wakeUpEvent) {
        if (!wakeUpEvent.isFoodTime()) {
            System.out.println("抱宝贝出去玩");
        }
    }
}
