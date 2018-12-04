package com.xym.myJava.design_pattern.工厂方法;

import com.xym.myJava.design_pattern.简单工厂模式.Shape;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-12-04 11:31
 */
public class Test {
    public static void main(String[] args) {
        Factory circlefactory = new CircleFactory();
        Shape circle = circlefactory.getShape();
        circle.draw();
    }
}
