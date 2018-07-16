package com.xym.myJava.oop;

/**
 * protected，通过使用protected访问修饰实现经典的模板方法设计模式
 * protected：同包或不同包子类允许访问
 *
 * @author xym
 */
public class Base3 {

    protected int currentStep = 0;

    protected void step1() {

    }

    protected void step2() {

    }

    public void action() {
        this.currentStep = 1;
        this.step1();
        this.currentStep = 2;
        step2();
    }

}
