package com.xym.myJava.head_first._01;

/**
 * 火箭助力鸭--练习题
 *
 * @author xym
 * @create 2019-04-08 15:53
 */
public class SimUDuck extends Duck {

    public SimUDuck() {
        this.flyBehavior = new FlyRocketPowered();
        this.quackBehavior = new MuteQuack();
    }

    @Override
    public void display() {
        System.out.println("simu dock");
    }
}
