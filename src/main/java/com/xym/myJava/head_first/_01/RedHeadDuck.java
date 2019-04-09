package com.xym.myJava.head_first._01;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-08 15:39
 */
public class RedHeadDuck extends Duck {

    public RedHeadDuck() {
        this.flyBehavior = new FlyWithWings();
        this.quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("红头鸭");
    }
}
