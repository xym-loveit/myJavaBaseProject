package com.xym.myJava.observer.v4;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 11:41
 */
public class ObserverTest4 {
    public static void main(String[] args) {
        Baby04 baby =new Baby04();
        baby.addListeners(new Father02());
        baby.addListeners(new Mother04());
        new Thread(baby).start();
    }
}
