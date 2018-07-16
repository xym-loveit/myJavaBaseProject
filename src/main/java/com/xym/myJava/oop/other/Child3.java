package com.xym.myJava.oop.other;

import com.xym.myJava.oop.Base3;

/**
 * 模板方法设计模式体现，展示使用protected访问修饰
 *
 * @author xym
 */
public class Child3 extends Base3 {

    @Override
    protected void step1() {
        System.out.println("child step1 " + this.currentStep);
    }

    @Override
    protected void step2() {
        System.out.println("child step2 " + this.currentStep);
    }

    public static void main(String[] args) {
        Child3 child3 = new Child3();
        child3.action();
    }
}
