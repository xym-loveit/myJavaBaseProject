package com.xym.myJava.observer.v1;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 11:17
 */
public class ObserverTest1 {
    public static void main(String[] args) {
        Baby baby = new Baby();
        new Thread(baby).start();
        new Thread(new Mother(baby)).start();
    }
}
