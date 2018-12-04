package com.xym.myJava.design_pattern.简单工厂模式;

/**
 * 圆形--图形实现类
 *
 * @author xym
 * @create 2018-12-04 11:11
 */
public class Circle implements Shape {

    public Circle() {
        System.out.println("Circle");
    }

    @Override
    public void draw() {
        System.out.println("Draw Circle");
    }
}
