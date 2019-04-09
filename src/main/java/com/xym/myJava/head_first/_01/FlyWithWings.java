package com.xym.myJava.head_first._01;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-08 15:44
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("正常的翅膀飞行");
    }
}
