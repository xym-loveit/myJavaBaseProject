package com.xym.myJava.observer.v3;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 11:27
 */
public class ObserverTest3 {
    public static void main(String[] args) {
        Baby03 baby =new Baby03(new Mother03(),new Father());
        new Thread(baby).start();
    }
}
