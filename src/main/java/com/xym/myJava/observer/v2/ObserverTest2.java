package com.xym.myJava.observer.v2;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 11:22
 */
public class ObserverTest2 {
    public static void main(String[] args) {
        Baby02 baby = new Baby02(new Mother02());
        new Thread(baby).start();
    }
}
