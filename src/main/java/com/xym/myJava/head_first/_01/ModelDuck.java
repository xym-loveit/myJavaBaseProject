package com.xym.myJava.head_first._01;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-08 16:20
 */
public class ModelDuck extends Duck {

    public ModelDuck() {
        //最开始模型鸭不会飞
        this.flyBehavior = new FlyNoWay();
        this.quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("模型鸭");
    }
}
