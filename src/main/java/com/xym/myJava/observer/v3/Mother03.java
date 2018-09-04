package com.xym.myJava.observer.v3;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 11:25
 */
public class Mother03 {
    public void feed(WakeUpEvent wakeUpEvent) {
        if (wakeUpEvent.isFoodTime()) {
            System.out.println("给宝贝喂食");
        }
    }
}
