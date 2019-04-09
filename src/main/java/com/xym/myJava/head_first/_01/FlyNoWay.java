package com.xym.myJava.head_first._01;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-08 15:45
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("不会飞");
    }
}
