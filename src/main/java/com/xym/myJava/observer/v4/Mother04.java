package com.xym.myJava.observer.v4;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 11:30
 */
public class Mother04 implements WakeUpListener {
    @Override
    public void actiontoWakenUp(WakeUpEvent02 wakeUpEvent) {
        if (wakeUpEvent.isFoodTime()) {
            System.out.println("给宝贝喂食");
        }
    }
}
