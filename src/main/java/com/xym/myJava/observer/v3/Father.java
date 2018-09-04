package com.xym.myJava.observer.v3;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 11:25
 */
public class Father {

    public void play(WakeUpEvent wakeUpEvent) {
        if (!wakeUpEvent.isFoodTime()) {
            System.out.println("抱宝贝出去玩");
        }
    }

}
