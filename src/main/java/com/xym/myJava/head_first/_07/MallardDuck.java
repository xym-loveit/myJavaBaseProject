package com.xym.myJava.head_first._07;

/**
 * 绿头鸭，鸭子类的具体实现
 *
 * @author xym
 * @create 2019-04-12 10:04
 */
public class MallardDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying");
    }
}
