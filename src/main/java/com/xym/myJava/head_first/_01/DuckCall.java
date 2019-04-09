package com.xym.myJava.head_first._01;

/**
 * 鸭鸣器---练习题
 *
 * @author xym
 * @create 2019-04-08 15:54
 */
public class DuckCall extends Duck {

    public DuckCall() {
        this.quackBehavior = new Quack();
        this.flyBehavior = new FlyNoWay();
    }

    @Override
    public void display() {
        System.out.println("鸭鸣器");
    }
}
