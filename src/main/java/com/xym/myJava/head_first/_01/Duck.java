package com.xym.myJava.head_first._01;

/**
 * 鸭子超类
 *
 * @author xym
 * @create 2019-04-08 15:31
 */
public abstract class Duck {

    /**
     * 行为接口类型引用变量，所有鸭子子类就继承他们
     */
    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

    //protected Duck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
    //    this.flyBehavior = flyBehavior;
    //    this.quackBehavior = quackBehavior;
    //}

    /**
     * 该方法可以保证动态的改变对象的行为，使其具有动态性
     *
     * @param flyBehavior
     */
    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    /**
     * 该方法可以保证动态的改变对象的行为，使其具有动态性
     *
     * @param quackBehavior
     */
    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    /**
     * 叫声-行为
     */
    public void performQuack() {
        this.quackBehavior.quack();
    }

    /**
     * 飞行-行为
     */
    public void performFly() {
        this.flyBehavior.fly();
    }

    /**
     * 鸭子显示方法
     */
    public abstract void display();
}
