package com.xym.myJava.head_first._01;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-08 15:50
 */
public class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("火箭动力飞行");
    }
}
