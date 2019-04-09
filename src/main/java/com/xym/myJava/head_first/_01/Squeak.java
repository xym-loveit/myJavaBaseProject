package com.xym.myJava.head_first._01;

/**
 * 橡皮泥鸭子(不会飞也不会叫)
 *
 * @author xym
 * @create 2019-04-08 15:46
 */
public class Squeak extends Duck {

    public Squeak() {
        this.flyBehavior = new FlyNoWay();
        this.quackBehavior = new MuteQuack();
    }

    @Override
    public void display() {
        System.out.println("橡皮泥鸭");
    }
}
